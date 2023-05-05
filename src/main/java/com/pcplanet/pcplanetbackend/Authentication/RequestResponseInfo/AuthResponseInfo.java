package com.pcplanet.pcplanetbackend.Authentication.RequestResponseInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseInfo {
    private String jwtToken;
}
