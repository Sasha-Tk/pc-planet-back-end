package com.pcplanet.pcplanetbackend.authentication.user;

import com.pcplanet.pcplanetbackend.authentication.request_response_info.AuthResponseInfo;
import com.pcplanet.pcplanetbackend.authentication.request_response_info.UserAuthInfo;
import com.pcplanet.pcplanetbackend.authentication.request_response_info.UserRegisterInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService userAuthService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseInfo> registerUser(@RequestBody UserRegisterInfo newUser) {
        return ResponseEntity.ok(userAuthService.registerNewUser(newUser));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseInfo> authenticateUser(@RequestBody UserAuthInfo user) {
        return ResponseEntity.ok(userAuthService.authenticateUser(user));
    }
}
