package com.pcplanet.pcplanetbackend.component.ram;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RAMResponseMapper {
    public RAMResponseDTO mapToDTO(RAM ram) {
        return new RAMResponseDTO(
                ram.getId(),
                ram.getSku(),
                ram.getComponentName(),
                List.of(ram.getVendor().getVendorName()),
                List.of(ram.getMemoryType()),
                List.of(ram.getMemoryAmount()),
                List.of(ram.getMemoryFrequency()),
                List.of(ram.getBarsNumber()),
                List.of(ram.getFormFactor()),
                ram.getImageURL(),
                ram.getLowerPrice()
        );
    }
}
