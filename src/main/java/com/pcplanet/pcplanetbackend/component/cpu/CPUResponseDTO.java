package com.pcplanet.pcplanetbackend.component.cpu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CPUResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> cpuFamily;
    private List<String> socket;
    private List<Short> coreCount;
    private List<Short> threadCount;
    private List<Short> cpuFrequency;
    private List<String> memoryType;
    private List<Short> tdpPower;
    private String imageURL;
    private Integer lowerPrice;
}
