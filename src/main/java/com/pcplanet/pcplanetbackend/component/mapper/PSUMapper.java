package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.psu.PSU;
import com.pcplanet.pcplanetbackend.component.psu.PSUDTO;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PSUMapper implements Mapper<PSUDTO, PSU> {
    private final VendorService vendorService;

    @Override
    public PSU mapToEntity(PSUDTO psuDTO) {
        return new PSU(
                psuDTO.getComponentName(),
                psuDTO.getSku(),
                psuDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        psuDTO.getVendor()),
                psuDTO.getImageURL(),
                psuDTO.getPower(),
                psuDTO.getCpuPowerConnector(),
                psuDTO.getNumberOfAdditionalPowerConnectorsForGpu(),
                psuDTO.getCooling(),
                psuDTO.getTypeOfAdditionalPowerConnectorsForGpu(),
                psuDTO.getNumberOfSataConnectors(),
                psuDTO.getSize().get(0),
                psuDTO.getSize().get(1),
                psuDTO.getSize().size() >= 3 ? psuDTO.getSize().get(2) : null
        );
    }
}
