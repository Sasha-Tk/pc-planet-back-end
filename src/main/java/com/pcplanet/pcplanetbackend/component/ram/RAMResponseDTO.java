package com.pcplanet.pcplanetbackend.component.ram;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RAMResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> memoryType;
    private List<Short> memoryAmount;
    private List<Short> memoryFrequency;
    private List<Short> barsNumber;
    private List<String> formFactor;
    private String imageURL;
    private Integer lowerPrice;
}
