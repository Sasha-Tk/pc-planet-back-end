package com.pcplanet.pcplanetbackend.Exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Data
public class ExceptionMessage {
    private final String message;
    private final String details;
    private final Instant time = Instant.now().truncatedTo(ChronoUnit.SECONDS);
}
