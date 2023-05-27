package com.pcplanet.pcplanetbackend.component.ssd.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SSDFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> driveCapacity;
    private List<String> sddConnectionInterface;
    private List<String> formFactor;
    private List<Short> readSpeed;
    private List<Short> writeSpeed;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "readSpeed",
                "writeSpeed"
        );
    }
}
