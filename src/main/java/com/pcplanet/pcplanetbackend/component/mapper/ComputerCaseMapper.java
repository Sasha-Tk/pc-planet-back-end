package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCase;
import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCaseDTO;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactorService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComputerCaseMapper implements Mapper<ComputerCaseDTO, ComputerCase> {
    private final VendorService vendorService;
    private final MotherboardFormFactorService motherboardFormFactorService;

    @Override
    public ComputerCase mapToEntity(ComputerCaseDTO computerCaseDTO) {
        return new ComputerCase(
                computerCaseDTO.getComponentName(),
                computerCaseDTO.getSku(),
                computerCaseDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        computerCaseDTO.getVendor()),
                computerCaseDTO.getImageURL(),
                computerCaseDTO.getMotherboardFormFactor() == null ?
                        null :
                        motherboardFormFactorService.createFormFactorsOrGetExisting(computerCaseDTO.getMotherboardFormFactor()),
                computerCaseDTO.getMaxCpuFanHeight(),
                computerCaseDTO.getMaxGpuWidth(),
                computerCaseDTO.getCaseFanCount(),
                computerCaseDTO.getSize().get(0),
                computerCaseDTO.getSize().get(1),
                computerCaseDTO.getSize().size() >= 3 ? computerCaseDTO.getSize().get(2) : null
        );
    }
}
