package com.pcplanet.pcplanetbackend.component.motherboard;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MotherboardResponseMapper {
    public MotherboardResponseDTO mapToDTO(Motherboard motherboard) {
        return new MotherboardResponseDTO(
                motherboard.getId(),
                motherboard.getSku(),
                motherboard.getComponentName(),
                List.of(motherboard.getVendor().getVendorName()),
                List.of(motherboard.getSocket().getSocketName()),
                List.of(motherboard.getChipset()),
                List.of(motherboard.getFormFactor().getFormFactorName()),
                List.of(motherboard.getMemoryType()),
                List.of(motherboard.getMemorySlotsAmount()),
                List.of(motherboard.getMaxRamAmount()),
                List.of(motherboard.getSataConnectorsAmount()),
                List.of(motherboard.getM2ConnectorsAmount()),
                motherboard.getImageURL(),
                motherboard.getLowerPrice()
        );
    }
}
