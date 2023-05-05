package com.pcplanet.pcplanetbackend.Authentication.User;

import com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo.AuthResponseInfo;
import com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo.UserAuthInfo;
import com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo.UserRegisterInfo;
import com.pcplanet.pcplanetbackend.UserInformation.UserInformation;
import com.pcplanet.pcplanetbackend.UserInformation.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserInformationRepository userInformationRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtEncoder jwtEncoder;

    public Boolean emailAlreadyExist(String email) {
        return userInformationRepository.existsUserInformationByEmail(email);
    }

    public UserInformation registerNewUser(UserRegisterInfo newUser) {
        Assert.notNull(newUser.getEmail(), "Email is null");
        Assert.notNull(newUser.getUsername(), "Username is null");
        Assert.notNull(newUser.getPassword(), "Password is null");
        Assert.state(!emailAlreadyExist(newUser.getEmail()), String.format("User with email '%s' already exist", newUser.getEmail()));

        UserInformation user = new UserInformation(
                newUser.getEmail(),
                newUser.getUsername(),
                new BCryptPasswordEncoder().encode(newUser.getPassword())
        );
        userInformationRepository.save(user);
        return user;
    }


    public AuthResponseInfo authenticateUser(UserAuthInfo user) {
        Assert.notNull(user.getEmail(), "Email is null");
        Assert.notNull(user.getPassword(), "Password is null");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        ));
        UserDetails userByEmail = userDetailsService.loadUserByUsername(user.getEmail());
        String roles = userByEmail
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.SECONDS))
                .subject(userByEmail.getUsername())
                .claim("roles", roles)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        return new AuthResponseInfo(jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue());
    }
}
