package com.pcplanet.pcplanetbackend.component.hdd;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HDDResponseMapper {
    public HDDResponseDTO mapToDTO(HDD hdd) {
        return new HDDResponseDTO(
                hdd.getId(),
                hdd.getSku(),
                hdd.getComponentName(),
                List.of(hdd.getVendor().getVendorName()),
                List.of(hdd.getDriveCapacity()),
                List.of(hdd.getHddConnectionInterface()),
                List.of(hdd.getFormFactor()),
                List.of(hdd.getDataTransferSpeed()),
                List.of(hdd.getRotationSpeed()),
                hdd.getImageURL(),
                hdd.getLowerPrice()
        );
    }
}
