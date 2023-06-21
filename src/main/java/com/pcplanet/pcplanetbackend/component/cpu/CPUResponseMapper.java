package com.pcplanet.pcplanetbackend.component.cpu;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CPUResponseMapper {
    public CPUResponseDTO mapToDTO(CPU cpu) {
        return new CPUResponseDTO(
                cpu.getId(),
                cpu.getSku(),
                cpu.getComponentName(),
                List.of(cpu.getVendor().getVendorName()),
                List.of(cpu.getCpuFamily()),
                List.of(cpu.getSocket().getSocketName()),
                List.of(cpu.getCoreCount()),
                List.of(cpu.getThreadCount()),
                List.of(cpu.getCpuFrequency()),
                List.of(cpu.getMemoryType()),
                List.of(cpu.getTdpPower()),
                cpu.getImageURL(),
                cpu.getLowerPrice()
        );
    }
}
