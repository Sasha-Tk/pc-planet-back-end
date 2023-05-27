package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.ram.RAM;
import com.pcplanet.pcplanetbackend.component.ram.RAMDTO;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RAMMapper implements Mapper<RAMDTO, RAM> {
    private final VendorService vendorService;

    @Override
    public RAM mapToEntity(RAMDTO ramDTO) {
        return new RAM(
                ramDTO.getComponentName(),
                ramDTO.getSku(),
                ramDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        ramDTO.getVendor()),
                ramDTO.getImageURL(),
                ramDTO.getMemoryType(),
                ramDTO.getMemoryAmount(),
                ramDTO.getMemoryFrequency(),
                ramDTO.getBarsNumber(),
                ramDTO.getFormFactor(),
                null,
                null,
                null
        );
    }
}
