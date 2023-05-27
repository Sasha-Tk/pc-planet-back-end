package com.pcplanet.pcplanetbackend.component.cpu_fan;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CPUFanDTO extends ComponentDTO {
    private List<String> socket;
    private Short fanSize;
    private List<String> fanPowerConnector;
    private Short maxTdp;
    private Float maxNoiseLevel;
    private Short cpuFanCount;
}
