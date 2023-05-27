package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilter;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PSUFilterMapper implements Mapper<PSUFilterDTO, PSUFilter> {
    private final VendorService vendorService;

    @Override
    public PSUFilter mapToEntity(PSUFilterDTO psuFilterDTO) {
        if (psuFilterDTO == null) {
            psuFilterDTO = new PSUFilterDTO();
        }
        return new PSUFilter(
                psuFilterDTO.getVendor() == null ?
                        null :
                        psuFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                psuFilterDTO.getPower(),
                psuFilterDTO.getCpuPowerConnector(),
                psuFilterDTO.getNumberOfAdditionalPowerConnectorsForGpu(),
                psuFilterDTO.getCooling(),
                psuFilterDTO.getTypeOfAdditionalPowerConnectorsForGpu(),
                psuFilterDTO.getNumberOfSataConnectors()
        );
    }

    @Override
    public PSUFilterDTO mapToDTO(PSUFilter psuFilter) {
        return new PSUFilterDTO(
                psuFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                psuFilter.getPower(),
                psuFilter.getCpuPowerConnector(),
                psuFilter.getNumberOfAdditionalPowerConnectorsForGpu(),
                psuFilter.getCooling(),
                psuFilter.getTypeOfAdditionalPowerConnectorsForGpu(),
                psuFilter.getNumberOfSataConnectors()
        );
    }
}
