package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilter;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RAMFilterMapper implements Mapper<RAMFilterDTO, RAMFilter> {
    private final VendorService vendorService;

    @Override
    public RAMFilter mapToEntity(RAMFilterDTO ramFilterDTO) {
        if (ramFilterDTO == null) {
            ramFilterDTO = new RAMFilterDTO();
        }
        return new RAMFilter(
                ramFilterDTO.getVendor() == null ?
                        null :
                        ramFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                ramFilterDTO.getMemoryType(),
                ramFilterDTO.getMemoryAmount(),
                ramFilterDTO.getMemoryFrequency(),
                ramFilterDTO.getBarsNumber(),
                ramFilterDTO.getFormFactor()
        );
    }

    @Override
    public RAMFilterDTO mapToDTO(RAMFilter ramFilter) {
        return new RAMFilterDTO(
                ramFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                ramFilter.getMemoryType(),
                ramFilter.getMemoryAmount(),
                ramFilter.getMemoryFrequency(),
                ramFilter.getBarsNumber(),
                ramFilter.getFormFactor()
        );
    }
}
