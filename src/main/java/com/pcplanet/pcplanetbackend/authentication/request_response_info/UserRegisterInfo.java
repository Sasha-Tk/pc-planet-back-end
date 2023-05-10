package com.pcplanet.pcplanetbackend.authentication.request_response_info;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterInfo {

    private String email;

    private String username;

    private String password;
}
