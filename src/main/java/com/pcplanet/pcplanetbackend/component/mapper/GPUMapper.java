package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.gpu.GPU;
import com.pcplanet.pcplanetbackend.component.gpu.GPUDTO;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GPUMapper implements Mapper<GPUDTO, GPU> {
    private final VendorService vendorService;
    private final GPUOutputInterfaceService gpuOutputInterfaceService;

    @Override
    public GPU mapToEntity(GPUDTO gpuDTO) {
        return new GPU(
                gpuDTO.getComponentName(),
                gpuDTO.getSku(),
                gpuDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        gpuDTO.getVendor()),
                gpuDTO.getImageURL(),
                gpuDTO.getChip(),
                gpuDTO.getChipFrequency(),
                gpuDTO.getMemoryAmount(),
                gpuDTO.getMemoryType(),
                gpuDTO.getMemoryFrequency(),
                gpuDTO.getMemoryInterfaceWidth(),
                gpuDTO.getConnectionInterface(),
                gpuDTO.getAdditionalPower(),
                gpuDTO.getRecommendedPsuPower(),
                gpuDTO.getSize().get(0),
                gpuDTO.getSize().get(1),
                gpuDTO.getSize().size() >= 3 ? gpuDTO.getSize().get(2) : null,
                gpuDTO.getOutputInterfaces() == null ?
                        null :
                        gpuOutputInterfaceService.createInterfacesOrGetExisting(gpuDTO.getOutputInterfaces())
        );
    }
}
