package com.pcplanet.pcplanetbackend.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionMessage {
    private String message;
    private String details;
}
