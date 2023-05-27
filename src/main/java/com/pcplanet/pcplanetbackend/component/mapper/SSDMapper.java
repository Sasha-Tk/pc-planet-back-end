package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.hdd.HDD;
import com.pcplanet.pcplanetbackend.component.hdd.HDDDTO;
import com.pcplanet.pcplanetbackend.component.ssd.SSD;
import com.pcplanet.pcplanetbackend.component.ssd.SSDDTO;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SSDMapper implements Mapper<SSDDTO, SSD> {
    private final VendorService vendorService;

    @Override
    public SSD mapToEntity(SSDDTO ssdDTO) {
        return new SSD(
                ssdDTO.getComponentName(),
                ssdDTO.getSku(),
                ssdDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        ssdDTO.getVendor()),
                ssdDTO.getImageURL(),
                ssdDTO.getDriveCapacity(),
                ssdDTO.getSddConnectionInterface(),
                ssdDTO.getFormFactor(),
                ssdDTO.getReadSpeed(),
                ssdDTO.getWriteSpeed(),
                null,
                null,
                null
        );
    }
}
