package com.pcplanet.pcplanetbackend.build;

import lombok.Data;

@Data
public class BuildDTO {
    private String gpu;
    private String psu;
    private String motherboard;
    private String cpu;
    private String ram;
    private String computerCase;
}
