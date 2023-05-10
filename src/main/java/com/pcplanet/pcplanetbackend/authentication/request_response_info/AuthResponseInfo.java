package com.pcplanet.pcplanetbackend.authentication.request_response_info;

import com.pcplanet.pcplanetbackend.user_information.UserInformation;
import lombok.Data;

@Data
public class AuthResponseInfo {
    private Long id;
    private String username;
    private final String jwtToken;

    public AuthResponseInfo(UserInformation userInformation, String jwtToken) {
        this.id = userInformation.getId();
        this.username = userInformation.getUsername();
        this.jwtToken = jwtToken;
    }
}
