package com.pcplanet.pcplanetbackend.component.computer_case;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ComputerCaseResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> motherboardFormFactor;
    private List<Float> maxCpuFanHeight;
    private List<Float> maxGpuWidth;
    private List<Short> caseFanCount;
    private List<Float> size;
    private String imageURL;
    private Integer lowerPrice;
}
