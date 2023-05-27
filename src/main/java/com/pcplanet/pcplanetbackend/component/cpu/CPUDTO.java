package com.pcplanet.pcplanetbackend.component.cpu;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CPUDTO extends ComponentDTO {
    private String cpuFamily;
    private String socket;
    private Short coreCount;
    private Short threadCount;
    private Short cpuFrequency;
    private String memoryType;
    private Short tdpPower;
}
