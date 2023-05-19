package com.pcplanet.pcplanetbackend.component.gpu.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPUFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> chip;
    private List<Short> memoryAmount;
    private List<String> memoryType;
    private List<Integer> memoryInterfaceWidth;
    private List<String> connectionInterface;
    private List<String> additionalPower;
    private List<String> outputInterfaces;
    private List<Short> recommendedPsuPower;
    private List<Float> width;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "recommendedPsuPower",
                "width"
        );
    }
}
