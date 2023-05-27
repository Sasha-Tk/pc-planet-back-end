package com.pcplanet.pcplanetbackend.component.ram.filter;

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
public class RAMFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<String> memoryType = new ArrayList<>();
    private List<Short> memoryAmount = new ArrayList<>();
    private List<Short> memoryFrequency = new ArrayList<>();
    private List<Short> barsNumber = new ArrayList<>();
    private List<String> formFactor = new ArrayList<>();
}
