package com.pcplanet.pcplanetbackend.component.computer_case.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerCaseFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> motherboardFormFactor;
    private List<Float> maxCpuFanHeight;
    private List<Float> maxGpuWidth;
    private List<Short> caseFanCount;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "maxCpuFanHeight",
                "maxGpuWidth",
                "caseFanCount"
        );
    }
}
