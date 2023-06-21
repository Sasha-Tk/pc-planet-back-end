package com.pcplanet.pcplanetbackend.component.ssd;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SSDResponseMapper {
    public SSDResponseDTO mapToDTO(SSD ssd) {
        return new SSDResponseDTO(
                ssd.getId(),
                ssd.getSku(),
                ssd.getComponentName(),
                List.of(ssd.getVendor().getVendorName()),
                List.of(ssd.getDriveCapacity()),
                List.of(ssd.getSddConnectionInterface()),
                List.of(ssd.getFormFactor()),
                List.of(ssd.getReadSpeed()),
                List.of(ssd.getWriteSpeed()),
                ssd.getImageURL(),
                ssd.getLowerPrice()
        );
    }
}
