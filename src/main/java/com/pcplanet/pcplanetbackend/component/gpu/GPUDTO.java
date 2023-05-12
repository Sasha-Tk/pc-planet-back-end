package com.pcplanet.pcplanetbackend.component.gpu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GPUDTO {
    private String componentName;
    private String sku;
    private String vendorName;
    private String chip;
    private Short chipFrequency;
    private Short memoryAmount;
    private String memoryType;
    private Integer memoryFrequency;
    private Integer memoryInterfaceWidth;
    private String connectionInterface;
    private String additionalPower;
    private Short recommendedPsuPower;
    private List<Float> size;
    private List<String> outputInterfaces;
}
