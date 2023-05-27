package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.hdd.filter.HDDFilter;
import com.pcplanet.pcplanetbackend.component.hdd.filter.HDDFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HDDFilterMapper implements Mapper<HDDFilterDTO, HDDFilter> {
    private final VendorService vendorService;

    @Override
    public HDDFilter mapToEntity(HDDFilterDTO hddFilterDTO) {
        if (hddFilterDTO == null) {
            hddFilterDTO = new HDDFilterDTO();
        }
        return new HDDFilter(
                hddFilterDTO.getVendor() == null ?
                        null :
                        hddFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                hddFilterDTO.getDriveCapacity(),
                hddFilterDTO.getFormFactor(),
                hddFilterDTO.getDataTransferSpeed(),
                hddFilterDTO.getRotationSpeed()
        );
    }

    @Override
    public HDDFilterDTO mapToDTO(HDDFilter hddFilter) {
        return new HDDFilterDTO(
                hddFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                hddFilter.getDriveCapacity(),
                hddFilter.getFormFactor(),
                hddFilter.getDataTransferSpeed(),
                hddFilter.getRotationSpeed()
        );
    }
}
