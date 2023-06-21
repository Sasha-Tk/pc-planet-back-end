package com.pcplanet.pcplanetbackend.component.cpu_fan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CPUFanResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> socket;
    private List<Short> fanSize;
    private List<String> fanPowerConnector;
    private List<Short> maxTdp;
    private List<Float> maxNoiseLevel;
    private List<Short> cpuFanCount;
    private List<Float> size;
    private String imageURL;
    private Integer lowerPrice;
}
