package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnectorService;
import com.pcplanet.pcplanetbackend.component.cpu.socket.SocketService;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFan;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFanDTO;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CPUFanMapper implements Mapper<CPUFanDTO, CPUFan> {
    private final VendorService vendorService;
    private final SocketService socketService;
    private final FanPowerConnectorService fanPowerConnectorService;

    @Override
    public CPUFan mapToEntity(CPUFanDTO cpuFanDTO) {
        try {

            return new CPUFan(
                    cpuFanDTO.getComponentName(),
                    cpuFanDTO.getSku(),
                    cpuFanDTO.getVendor() == null ?
                            null :
                            vendorService
                                    .createVendorOrGetExisting(
                                            cpuFanDTO.getVendor()),
                    cpuFanDTO.getImageURL(),
                    cpuFanDTO.getCpuFanCount() == null ?
                            null :
                            socketService.createSocketsOrGetExisting(cpuFanDTO.getSocket()),
                    cpuFanDTO.getFanSize(),
                    cpuFanDTO.getFanPowerConnector() == null ?
                            null :
                            fanPowerConnectorService.createPowerConnectorsOrGetExisting(cpuFanDTO.getFanPowerConnector()),
                    cpuFanDTO.getMaxTdp(),
                    cpuFanDTO.getMaxNoiseLevel(),
                    cpuFanDTO.getCpuFanCount(),
                    cpuFanDTO.getSize().get(0),
                    cpuFanDTO.getSize().get(1),
                    cpuFanDTO.getSize().size() >= 3 ? cpuFanDTO.getSize().get(2) : null
            );
        } catch (RuntimeException e) {
            System.out.println(cpuFanDTO.getSku());
        }
        return new CPUFan();
    }
}
