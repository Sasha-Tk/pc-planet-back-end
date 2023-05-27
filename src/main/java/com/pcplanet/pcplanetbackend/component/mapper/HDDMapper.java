package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.hdd.HDD;
import com.pcplanet.pcplanetbackend.component.hdd.HDDDTO;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HDDMapper implements Mapper<HDDDTO, HDD> {
    private final VendorService vendorService;

    @Override
    public HDD mapToEntity(HDDDTO hddDTO) {
        return new HDD(
                hddDTO.getComponentName(),
                hddDTO.getSku(),
                hddDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        hddDTO.getVendor()),
                hddDTO.getImageURL(),
                hddDTO.getDriveCapacity(),
                hddDTO.getHddConnectionInterface(),
                hddDTO.getFormFactor(),
                hddDTO.getDataTransferSpeed(),
                hddDTO.getRotationSpeed(),
                hddDTO.getSize().get(0),
                hddDTO.getSize().get(1),
                hddDTO.getSize().get(2)
        );
    }
}
