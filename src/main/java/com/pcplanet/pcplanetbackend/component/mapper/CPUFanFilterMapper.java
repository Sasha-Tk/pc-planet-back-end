package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnectorService;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilter;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.cpu.socket.SocketService;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFan;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFanDTO;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilter;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CPUFanFilterMapper implements Mapper<CPUFanFilterDTO, CPUFanFilter> {
    private final VendorService vendorService;
    private final SocketService socketService;
    private final FanPowerConnectorService fanPowerConnectorService;

    @Override
    public CPUFanFilter mapToEntity(CPUFanFilterDTO cpuFanFilterDTO) {
        if (cpuFanFilterDTO == null) {
            cpuFanFilterDTO = new CPUFanFilterDTO();
        }
        return new CPUFanFilter(
                cpuFanFilterDTO.getVendor() == null ?
                        null :
                        cpuFanFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                cpuFanFilterDTO.getSocket() == null ?
                        null :
                        cpuFanFilterDTO.getSocket()
                                .stream()
                                .map(socketService::findSocketByName)
                                .toList(),
                cpuFanFilterDTO.getFanSize(),
                cpuFanFilterDTO.getFanPowerConnector() == null ?
                        null :
                        cpuFanFilterDTO.getFanPowerConnector()
                                .stream()
                                .map(fanPowerConnectorService::findPowerConnectorByPowerConnectorName)
                                .toList(),
                cpuFanFilterDTO.getMaxTdp(),
                cpuFanFilterDTO.getMaxNoiseLevel(),
                cpuFanFilterDTO.getCpuFanCount(),
                cpuFanFilterDTO.getHeight()
        );
    }

    @Override
    public CPUFanFilterDTO mapToDTO(CPUFanFilter cpuFanFilter) {
        return new CPUFanFilterDTO(
                cpuFanFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                cpuFanFilter.getSocket()
                        .stream()
                        .map(Socket::getSocketName)
                        .toList(),
                cpuFanFilter.getFanSize(),
                cpuFanFilter.getFanPowerConnector()
                        .stream()
                        .map(FanPowerConnector::getPowerConnectorName)
                        .toList(),
                cpuFanFilter.getMaxTdp(),
                cpuFanFilter.getMaxNoiseLevel(),
                cpuFanFilter.getCpuFanCount(),
                cpuFanFilter.getHeight()
        );
    }
}
