package com.pcplanet.pcplanetbackend.component.cpu_fan.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
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
public class CPUFanFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<Socket> socket = new ArrayList<>();
    private List<Short> fanSize = new ArrayList<>();
    private List<FanPowerConnector> fanPowerConnector = new ArrayList<>();
    private List<Short> maxTdp = new ArrayList<>();
    private List<Float> maxNoiseLevel = new ArrayList<>();
    private List<Short> cpuFanCount = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "maxTdp",
                "maxNoiseLevel",
                "caseFanCount"
        );
    }
}
