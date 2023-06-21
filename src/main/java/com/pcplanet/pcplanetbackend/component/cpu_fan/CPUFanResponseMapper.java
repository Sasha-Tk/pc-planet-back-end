package com.pcplanet.pcplanetbackend.component.cpu_fan;

import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CPUFanResponseMapper {
    public CPUFanResponseDTO mapToDTO(CPUFan cpuFan) {
        return new CPUFanResponseDTO(
                cpuFan.getId(),
                cpuFan.getSku(),
                cpuFan.getComponentName(),
                List.of(cpuFan.getVendor().getVendorName()),
                cpuFan.getSocket().stream().map(Socket::getSocketName).toList(),
                List.of(cpuFan.getFanSize()),
                cpuFan.getFanPowerConnector().stream().map(FanPowerConnector::getPowerConnectorName).toList(),
                List.of(cpuFan.getMaxTdp()),
                List.of(cpuFan.getMaxNoiseLevel()),
                List.of(cpuFan.getCpuFanCount()),
                List.of(cpuFan.getWidth(), cpuFan.getDepth(), cpuFan.getHeight()),
                cpuFan.getImageURL(),
                cpuFan.getLowerPrice()
        );
    }
}
