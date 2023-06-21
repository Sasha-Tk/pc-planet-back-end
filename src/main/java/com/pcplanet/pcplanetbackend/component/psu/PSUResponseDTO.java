package com.pcplanet.pcplanetbackend.component.psu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PSUResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<Short> power;
    private List<String> cpuPowerConnector;
    private List<Short> numberOfAdditionalPowerConnectorsForGpu;
    private List<Short> cooling;
    private List<String> typeOfAdditionalPowerConnectorsForGpu;
    private List<Short> numberOfSataConnectors;
    private List<Float> size;
    private String imageURL;
    private Integer lowerPrice;
}
