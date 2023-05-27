package com.pcplanet.pcplanetbackend.component.motherboard.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardFilterDTO extends ComponentFilter {
    private List<String> vendor;
    private List<String> socket;
    private List<String> chipset;
    private List<String> formFactor;
    private List<String> memoryType;
    private List<Short> memorySlotsAmount;
    private List<Short> maxRamAmount;
    private List<Short> sataConnectorsAmount;
    private List<Short> m2ConnectorsAmount;

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "sataConnectorsAmount",
                "m2ConnectorsAmount"
        );
    }
}
