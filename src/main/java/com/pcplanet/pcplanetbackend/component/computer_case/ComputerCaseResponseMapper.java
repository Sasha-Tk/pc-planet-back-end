package com.pcplanet.pcplanetbackend.component.computer_case;

import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComputerCaseResponseMapper {
    public ComputerCaseResponseDTO mapToDTO(ComputerCase computerCase) {
        return new ComputerCaseResponseDTO(
                computerCase.getId(),
                computerCase.getSku(),
                computerCase.getComponentName(),
                List.of(computerCase.getVendor().getVendorName()),
                computerCase.getMotherboardFormFactor().stream().map(MotherboardFormFactor::getFormFactorName).toList(),
                List.of(computerCase.getMaxCpuFanHeight()),
                List.of(computerCase.getMaxGpuWidth()),
                List.of(computerCase.getCaseFanCount()),
                List.of(computerCase.getWidth(), computerCase.getDepth(), computerCase.getHeight()),
                computerCase.getImageURL(),
                computerCase.getLowerPrice()
        );
    }
}
