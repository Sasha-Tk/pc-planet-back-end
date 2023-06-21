package com.pcplanet.pcplanetbackend.component.gpu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GPUResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> chip;
    private List<Short> chipFrequency;
    private List<Short> memoryAmount;
    private List<String> memoryType;
    private List<Integer> memoryFrequency;
    private List<Integer> memoryInterfaceWidth;
    private List<String> connectionInterface;
    private List<String> additionalPower;
    private List<Short> recommendedPsuPower;
    private List<String> outputInterfaces;
    private List<Float> size;
    private String imageURL;
    private Integer lowerPrice;
}
