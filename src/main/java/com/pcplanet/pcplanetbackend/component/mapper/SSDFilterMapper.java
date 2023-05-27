package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilter;
import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SSDFilterMapper implements Mapper<SSDFilterDTO, SSDFilter> {
    private final VendorService vendorService;

    @Override
    public SSDFilter mapToEntity(SSDFilterDTO ssdFilterDTO) {
        if (ssdFilterDTO == null) {
            ssdFilterDTO = new SSDFilterDTO();
        }
        return new SSDFilter(
                ssdFilterDTO.getVendor() == null ?
                        null :
                        ssdFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                ssdFilterDTO.getDriveCapacity(),
                ssdFilterDTO.getSddConnectionInterface(),
                ssdFilterDTO.getFormFactor(),
                ssdFilterDTO.getReadSpeed(),
                ssdFilterDTO.getWriteSpeed()
        );
    }

    @Override
    public SSDFilterDTO mapToDTO(SSDFilter ssdFilter) {
        return new SSDFilterDTO(
                ssdFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                ssdFilter.getDriveCapacity(),
                ssdFilter.getSddConnectionInterface(),
                ssdFilter.getFormFactor(),
                ssdFilter.getReadSpeed(),
                ssdFilter.getWriteSpeed()
        );
    }
}
