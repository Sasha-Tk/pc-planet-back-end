package com.pcplanet.pcplanetbackend.component.computer_case.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerCaseFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<MotherboardFormFactor> motherboardFormFactor = new ArrayList<>();
    private List<Float> maxCpuFanHeight = new ArrayList<>();
    private List<Float> maxGpuWidth = new ArrayList<>();
    private List<Short> caseFanCount = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "maxCpuFanHeight",
                "maxGpuWidth",
                "caseFanCount"
        );
    }
}
