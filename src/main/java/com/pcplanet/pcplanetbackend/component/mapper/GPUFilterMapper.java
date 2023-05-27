package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilter;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterface;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceService;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GPUFilterMapper implements Mapper<GPUFilterDTO, GPUFilter> {
    private final VendorService vendorService;
    private final GPUOutputInterfaceService gpuOutputInterfaceService;

    @Override
    public GPUFilter mapToEntity(GPUFilterDTO gpuFilterDTO) {
        if (gpuFilterDTO == null) {
            gpuFilterDTO = new GPUFilterDTO();
        }
        return new GPUFilter(
                gpuFilterDTO.getVendor() == null ?
                        null :
                        gpuFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                gpuFilterDTO.getChip(),
                gpuFilterDTO.getMemoryAmount(),
                gpuFilterDTO.getMemoryType(),
                gpuFilterDTO.getMemoryInterfaceWidth(),
                gpuFilterDTO.getConnectionInterface(),
                gpuFilterDTO.getAdditionalPower(),
                gpuFilterDTO.getOutputInterfaces() == null ?
                        null :
                        gpuFilterDTO.getOutputInterfaces()
                                .stream()
                                .map(gpuOutputInterfaceService::findInterfaceByInterfaceName)
                                .toList(),
                gpuFilterDTO.getRecommendedPsuPower(),
                gpuFilterDTO.getWidth()
        );
    }

    @Override
    public GPUFilterDTO mapToDTO(GPUFilter gpuFilter) {
        return new GPUFilterDTO(
                gpuFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                gpuFilter.getChip(),
                gpuFilter.getMemoryAmount(),
                gpuFilter.getMemoryType(),
                gpuFilter.getMemoryInterfaceWidth(),
                gpuFilter.getConnectionInterface(),
                gpuFilter.getAdditionalPower(),
                gpuFilter.getOutputInterfaces()
                        .stream()
                        .map(GPUOutputInterface::getInterfaceName)
                        .toList(),
                gpuFilter.getRecommendedPsuPower(),
                gpuFilter.getWidth()
        );
    }
}
