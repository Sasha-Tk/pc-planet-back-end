package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GPUDTO extends ComponentDTO {
    private String chip;
    private Short chipFrequency;
    private Short memoryAmount;
    private String memoryType;
    private Integer memoryFrequency;
    private Integer memoryInterfaceWidth;
    private String connectionInterface;
    private String additionalPower;
    private Short recommendedPsuPower;
    private List<String> outputInterfaces;
}
