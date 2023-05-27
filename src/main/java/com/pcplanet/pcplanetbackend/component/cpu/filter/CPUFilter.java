package com.pcplanet.pcplanetbackend.component.cpu.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CPUFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<String> cpuFamily = new ArrayList<>();
    private List<Socket> socket = new ArrayList<>();
    private List<Short> coreCount = new ArrayList<>();
    private List<Short> threadCount = new ArrayList<>();
    private List<Short> cpuFrequency = new ArrayList<>();
    private List<String> memoryType = new ArrayList<>();
    private List<Short> tdpPower = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "tdpPower"
        );
    }
}
