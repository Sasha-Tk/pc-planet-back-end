package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GPUService {
    private final VendorService vendorService;
    private final GPUOutputInterfaceService gpuOutputInterfaceService;
    private final GPURepository gpuRepository;

    public GPU createGpu(GPUDTO gpuDTO) {
        return gpuRepository.save(
                new GPU(
                        gpuDTO.getComponentName(),
                        gpuDTO.getSku(),
                        vendorService
                                .createVendor(gpuDTO.getVendorName()),
                        gpuDTO.getChip(),
                        gpuDTO.getChipFrequency(),
                        gpuDTO.getMemoryAmount(),
                        gpuDTO.getMemoryType(),
                        gpuDTO.getMemoryFrequency(),
                        gpuDTO.getMemoryInterface(),
                        gpuDTO.getConnectionInterface(),
                        gpuDTO.getAdditionalPower(),
                        gpuDTO.getRecommendedPsuPower(),
                        gpuDTO.getSize(),
                        gpuOutputInterfaceService
                                .createInterfaces(gpuDTO.getOutputInterfaces())
                )
        );
    }

}
