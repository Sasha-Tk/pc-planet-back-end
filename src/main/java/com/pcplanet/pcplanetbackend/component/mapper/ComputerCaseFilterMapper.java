package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilter;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilterDTO;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactorService;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComputerCaseFilterMapper implements Mapper<ComputerCaseFilterDTO, ComputerCaseFilter> {
    private final VendorService vendorService;
    private final MotherboardFormFactorService motherboardFormFactorService;

    @Override
    public ComputerCaseFilter mapToEntity(ComputerCaseFilterDTO computerCaseFilterDTO) {
        if (computerCaseFilterDTO == null) {
            computerCaseFilterDTO = new ComputerCaseFilterDTO();
        }
        return new ComputerCaseFilter(
                computerCaseFilterDTO.getVendor() == null ?
                        null :
                        computerCaseFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                computerCaseFilterDTO.getMotherboardFormFactor() == null ?
                        null :
                        computerCaseFilterDTO.getMotherboardFormFactor()
                                .stream()
                                .map(motherboardFormFactorService::findFormFactorByFormFactorName)
                                .toList(),
                computerCaseFilterDTO.getMaxCpuFanHeight(),
                computerCaseFilterDTO.getMaxGpuWidth(),
                computerCaseFilterDTO.getCaseFanCount()
        );
    }

    @Override
    public ComputerCaseFilterDTO mapToDTO(ComputerCaseFilter computerCaseFilter) {
        return new ComputerCaseFilterDTO(
                computerCaseFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                computerCaseFilter.getMotherboardFormFactor()
                        .stream()
                        .map(MotherboardFormFactor::getFormFactorName)
                        .toList(),
                computerCaseFilter.getMaxCpuFanHeight(),
                computerCaseFilter.getMaxGpuWidth(),
                computerCaseFilter.getCaseFanCount()
        );
    }
}
