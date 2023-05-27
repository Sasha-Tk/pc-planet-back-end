package com.pcplanet.pcplanetbackend.component.psu.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PSUFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<Short> power = new ArrayList<>();
    private List<String> cpuPowerConnector = new ArrayList<>();
    private List<Short> numberOfAdditionalPowerConnectorsForGpu = new ArrayList<>();
    private List<Short> cooling = new ArrayList<>();
    private List<String> typeOfAdditionalPowerConnectorsForGpu = new ArrayList<>();
    private List<Short> numberOfSataConnectors = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "power",
                "numberOfAdditionalPowerConnectorsForGpu",
                "numberOfSataConnectors"
        );
    }
}
