package com.pcplanet.pcplanetbackend.component.gpu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GPUFilterDTO {
    private List<String> vendor;
    private List<String> chip;
    private List<Short> memoryAmount;
    private List<String> memoryType;
    private List<Integer> memoryInterfaceWidth;
    private List<String> connectionInterface;
    private List<String> additionalPower;
    private List<String> outputInterfaces;
}
