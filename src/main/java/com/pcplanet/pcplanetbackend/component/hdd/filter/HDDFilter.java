package com.pcplanet.pcplanetbackend.component.hdd.filter;

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
public class HDDFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<String> driveCapacity = new ArrayList<>();
    private List<String> formFactor = new ArrayList<>();
    private List<Short> dataTransferSpeed = new ArrayList<>();
    private List<Short> rotationSpeed = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "dataTransferSpeed"
        );
    }
}
