package com.pcplanet.pcplanetbackend.Authentication.User;

import com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo.AuthResponseInfo;
import com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo.UserAuthInfo;
import com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo.UserRegisterInfo;
import com.pcplanet.pcplanetbackend.Authentication.Security.JWTService;
import com.pcplanet.pcplanetbackend.UserInformation.UserInformation;
import com.pcplanet.pcplanetbackend.UserInformation.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserInformationRepository userInformationRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

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
        Assert.notNull(user.getEmail(),"Email is null");
        Assert.notNull(user.getPassword(), "Password is null");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        UserDetails userByEmail = userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateJwtToken(userByEmail);
        return new AuthResponseInfo(jwtToken);
    }
}
