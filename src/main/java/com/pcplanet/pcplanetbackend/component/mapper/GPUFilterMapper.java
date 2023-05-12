package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.gpu.GPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.GPUFilter;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GPUFilterMapper implements Mapper<GPUFilterDTO, GPUFilter> {
    private final VendorService vendorService;

    @Override
    public GPUFilter mapToEntity(GPUFilterDTO gpuFilterDTO) {
        return new GPUFilter(
                gpuFilterDTO.getSku(),
                gpuFilterDTO.getChip(),
                gpuFilterDTO.getVendor() == null ?
                        null :
                        gpuFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList()
        );
    }
}
