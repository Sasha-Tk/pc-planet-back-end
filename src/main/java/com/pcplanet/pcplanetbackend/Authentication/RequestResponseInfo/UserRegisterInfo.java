package com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterInfo {

    private String email;

    private String username;

    private String password;
}
