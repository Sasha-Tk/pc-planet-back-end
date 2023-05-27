package com.pcplanet.pcplanetbackend.component.cpu.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CPUFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> cpuFamily;
    private List<String> socket;
    private List<Short> coreCount;
    private List<Short> threadCount;
    private List<Short> cpuFrequency;
    private List<String> memoryType;
    private List<Short> tdpPower;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "tdpPower"
        );
    }
}
