package com.pcplanet.pcplanetbackend.component.cpu_fan.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CPUFanFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> socket;
    private List<Short> fanSize;
    private List<String> fanPowerConnector;
    private List<Short> maxTdp;
    private List<Float> maxNoiseLevel;
    private List<Short> cpuFanCount;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "maxTdp",
                "maxNoiseLevel",
                "caseFanCount"
        );
    }
}
