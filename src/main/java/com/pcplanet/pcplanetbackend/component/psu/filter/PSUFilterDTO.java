package com.pcplanet.pcplanetbackend.component.psu.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PSUFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<Short> power;
    private List<String> cpuPowerConnector;
    private List<Short> numberOfAdditionalPowerConnectorsForGpu;
    private List<Short> cooling;
    private List<String> typeOfAdditionalPowerConnectorsForGpu;
    private List<Short> numberOfSataConnectors;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "power",
                "numberOfAdditionalPowerConnectorsForGpu",
                "numberOfSataConnectors"
        );
    }
}
