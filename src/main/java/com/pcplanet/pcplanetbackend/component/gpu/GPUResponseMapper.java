package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GPUResponseMapper {
    public GPUResponseDTO mapToDTO(GPU gpu) {
        return new GPUResponseDTO(
                gpu.getId(),
                gpu.getSku(),
                gpu.getComponentName(),
                List.of(gpu.getVendor().getVendorName()),
                List.of(gpu.getChip()),
                List.of(gpu.getChipFrequency()),
                List.of(gpu.getMemoryAmount()),
                List.of(gpu.getMemoryType()),
                List.of(gpu.getMemoryFrequency()),
                List.of(gpu.getMemoryInterfaceWidth()),
                List.of(gpu.getConnectionInterface()),
                List.of(gpu.getAdditionalPower()),
                List.of(gpu.getRecommendedPsuPower()),
                gpu.getOutputInterfaces().stream().map(GPUOutputInterface::getInterfaceName).toList(),
                List.of(gpu.getWidth(), gpu.getDepth(), gpu.getHeight()),
                gpu.getImageURL(),
                gpu.getLowerPrice()
        );
    }
}
