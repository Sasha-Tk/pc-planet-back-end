package com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthInfo {
    private String email;
    private String password;
}
