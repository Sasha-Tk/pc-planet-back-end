package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.gpu.GPU;
import com.pcplanet.pcplanetbackend.component.gpu.GPUDTO;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceService;
import com.pcplanet.pcplanetbackend.component.size.ComponentSizeService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class GPUMapper implements Mapper<GPUDTO, GPU> {
    private final VendorService vendorService;
    private final GPUOutputInterfaceService gpuOutputInterfaceService;
    private final ComponentSizeService componentSizeService;

    @Override
    public GPU mapToEntity(GPUDTO gpuDTO) {
        return new GPU(
                gpuDTO.getComponentName(),
                gpuDTO.getSku(),
                gpuDTO.getVendorName() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        gpuDTO.getVendorName()),
                gpuDTO.getChip(),
                gpuDTO.getChipFrequency(),
                gpuDTO.getMemoryAmount(),
                gpuDTO.getMemoryType(),
                gpuDTO.getMemoryFrequency(),
                gpuDTO.getMemoryInterfaceWidth(),
                gpuDTO.getConnectionInterface(),
                gpuDTO.getAdditionalPower(),
                gpuDTO.getRecommendedPsuPower(),
                gpuDTO.getSize() == null ?
                        null :
                        componentSizeService.createComponentSizeOrGetExisting(gpuDTO.getSize()),
                gpuDTO.getOutputInterfaces() == null ?
                        null :
                        gpuOutputInterfaceService.createInterfacesOrGetExisting(gpuDTO.getOutputInterfaces())
        );
    }
}
