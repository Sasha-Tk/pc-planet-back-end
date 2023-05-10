package com.pcplanet.pcplanetbackend.authentication.request_response_info;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthInfo {
    private String email;
    private String password;
}
