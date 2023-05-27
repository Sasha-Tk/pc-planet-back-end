package com.pcplanet.pcplanetbackend.component.case_fan.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseFanFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<Short> fanSize;
    private List<String> caseFanPowerConnector;
    private List<Short> rpm;
    private List<Float> maxNoiseLevel;
    private List<Short> caseFanCount;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "rpm",
                "maxNoiseLevel",
                "caseFanCount"
        );
    }
}
