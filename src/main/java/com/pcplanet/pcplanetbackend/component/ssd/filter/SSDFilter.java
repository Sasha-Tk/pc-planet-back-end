package com.pcplanet.pcplanetbackend.component.ssd.filter;

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
public class SSDFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<String> driveCapacity = new ArrayList<>();
    private List<String> sddConnectionInterface = new ArrayList<>();
    private List<String> formFactor = new ArrayList<>();
    private List<Short> readSpeed = new ArrayList<>();
    private List<Short> writeSpeed = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "readSpeed",
                "writeSpeed"
        );
    }
}
