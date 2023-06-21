package com.pcplanet.pcplanetbackend.build;

import lombok.Data;

@Data
public class BuildDTO {
    private Long id;
    private String motherboard;
    private String cpu;
    private String ram;
    private String gpu;
    private String psu;
    private String computerCase;
    private String ssd;
    private String hdd;
    private String cpuFan;
    private String caseFan;
}
