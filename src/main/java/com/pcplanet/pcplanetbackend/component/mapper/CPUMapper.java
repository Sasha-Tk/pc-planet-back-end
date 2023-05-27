package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.cpu.CPU;
import com.pcplanet.pcplanetbackend.component.cpu.CPUDTO;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.cpu.socket.SocketService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CPUMapper implements Mapper<CPUDTO, CPU> {
    private final VendorService vendorService;
    private final SocketService socketService;

    @Override
    public CPU mapToEntity(CPUDTO cpudto) {
        return new CPU(
                cpudto.getComponentName(),
                cpudto.getSku(),
                cpudto.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(cpudto.getVendor()),
                cpudto.getImageURL(),
                cpudto.getCpuFamily(),
                cpudto.getSocket() == null ?
                        null :
                        socketService
                                .createSocketOrGetExisting(cpudto.getSocket()),
                cpudto.getCoreCount(),
                cpudto.getThreadCount(),
                cpudto.getCpuFrequency(),
                cpudto.getMemoryType(),
                cpudto.getTdpPower()
        );
    }
}
