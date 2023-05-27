package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilter;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.cpu.socket.SocketService;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CPUFilterMapper implements Mapper<CPUFilterDTO, CPUFilter> {
    private final VendorService vendorService;
    private final SocketService socketService;

    @Override
    public CPUFilter mapToEntity(CPUFilterDTO cpuFilterDTO) {
        if (cpuFilterDTO == null) {
            cpuFilterDTO = new CPUFilterDTO();
        }
        return new CPUFilter(
                cpuFilterDTO.getVendor() == null ?
                        null :
                        cpuFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                cpuFilterDTO.getCpuFamily(),
                cpuFilterDTO.getSocket() == null ?
                        null :
                        cpuFilterDTO.getSocket()
                                .stream()
                                .map(socketService::findSocketByName)
                                .toList(),
                cpuFilterDTO.getCoreCount(),
                cpuFilterDTO.getThreadCount(),
                cpuFilterDTO.getCpuFrequency(),
                cpuFilterDTO.getMemoryType(),
                cpuFilterDTO.getTdpPower()
        );
    }

    @Override
    public CPUFilterDTO mapToDTO(CPUFilter cpuFilter) {
        return new CPUFilterDTO(
                cpuFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                cpuFilter.getCpuFamily(),
                cpuFilter.getSocket()
                        .stream()
                        .map(Socket::getSocketName)
                        .toList(),
                cpuFilter.getCoreCount(),
                cpuFilter.getThreadCount(),
                cpuFilter.getCpuFrequency(),
                cpuFilter.getMemoryType(),
                cpuFilter.getTdpPower()
        );
    }
}
