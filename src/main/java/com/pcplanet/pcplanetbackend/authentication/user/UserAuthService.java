package com.pcplanet.pcplanetbackend.authentication.user;

import com.pcplanet.pcplanetbackend.authentication.request_response_info.AuthResponseInfo;
import com.pcplanet.pcplanetbackend.authentication.request_response_info.UserAuthInfo;
import com.pcplanet.pcplanetbackend.authentication.request_response_info.UserRegisterInfo;
import com.pcplanet.pcplanetbackend.user_information.UserInformation;
import com.pcplanet.pcplanetbackend.user_information.UserInformationRepository;
import com.pcplanet.pcplanetbackend.user_information.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserInformationRepository userInformationRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserInformationService userInformationService;
    private final JwtEncoder jwtEncoder;

    public Boolean emailAlreadyExist(String email) {
        return userInformationRepository.existsUserInformationByEmail(email);
    }

    public AuthResponseInfo registerNewUser(UserRegisterInfo newUser) {
        Assert.notNull(newUser.getEmail(), "Email is null");
        Assert.notNull(newUser.getUsername(), "Username is null");
        Assert.notNull(newUser.getPassword(), "Password is null");
        Assert.state(!emailAlreadyExist(newUser.getEmail()), "User with email already exist");

        UserInformation user = new UserInformation(
                newUser.getEmail(),
                newUser.getUsername(),
                new BCryptPasswordEncoder().encode(newUser.getPassword())
        );
        UserInformation savedUser = userInformationRepository.save(user);
        String token = generateToken(savedUser);
        return new AuthResponseInfo(savedUser, token);
    }


    public AuthResponseInfo authenticateUser(UserAuthInfo user) {
        Assert.notNull(user.getEmail(), "Email is null");
        Assert.notNull(user.getPassword(), "Password is null");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        ));
        UserInformation userByEmail = userInformationService.getUserByEmail(user.getEmail());
        return new AuthResponseInfo(userByEmail, generateToken(userByEmail));
    }

    private String generateToken(UserInformation userInformation) {
        String role = userInformation.getUserRole().name();
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.DAYS))
                .subject(userInformation.getUsername())
                .claim("role", role)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
