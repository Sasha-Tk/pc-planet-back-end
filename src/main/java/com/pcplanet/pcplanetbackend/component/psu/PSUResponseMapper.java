package com.pcplanet.pcplanetbackend.component.psu;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PSUResponseMapper {
    public PSUResponseDTO mapToDTO(PSU psu) {
        return new PSUResponseDTO(
                psu.getId(),
                psu.getSku(),
                psu.getComponentName(),
                List.of(psu.getVendor().getVendorName()),
                List.of(psu.getPower()),
                List.of(psu.getCpuPowerConnector()),
                List.of(psu.getNumberOfAdditionalPowerConnectorsForGpu()),
                List.of(psu.getCooling()),
                List.of(psu.getTypeOfAdditionalPowerConnectorsForGpu()),
                List.of(psu.getNumberOfSataConnectors()),
                psu.getHeight() != null ?
                        List.of(psu.getWidth(), psu.getDepth(), psu.getHeight()) :
                        List.of(psu.getWidth(), psu.getDepth()),
                psu.getImageURL(),
                psu.getLowerPrice()
        );
    }
}
