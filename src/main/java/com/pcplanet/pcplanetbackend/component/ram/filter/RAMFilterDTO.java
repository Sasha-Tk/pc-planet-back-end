package com.pcplanet.pcplanetbackend.component.ram.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RAMFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> memoryType;
    private List<Short> memoryAmount;
    private List<Short> memoryFrequency;
    private List<Short> barsNumber;
    private List<String> formFactor;
}
