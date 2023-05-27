package com.pcplanet.pcplanetbackend.component.cpu_fan;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "cpu_fan")
@NoArgsConstructor
@Data
public class CPUFan extends Component {
    @ManyToMany
    @JoinTable(name = "fan_with_socket")
    private List<Socket> socket;
    private Short fanSize;
    @ManyToMany
    @JoinTable(name = "cpu_fan_with_power_connector")
    private List<FanPowerConnector> fanPowerConnector;
    private Short maxTdp;
    private Float maxNoiseLevel;
    private Short cpuFanCount;

    public CPUFan(String name,
                  String sku,
                  Vendor vendor,
                  String imageURL,
                  List<Socket> socket,
                  Short fanSize,
                  List<FanPowerConnector> fanPowerConnector,
                  Short maxTdp,
                  Float maxNoiseLevel,
                  Short cpuFanCount,
                  Float width,
                  Float depth,
                  Float height) {
        super(name, sku, vendor, width, depth, height, imageURL);
        this.socket = socket;
        this.fanSize = fanSize;
        this.fanPowerConnector = fanPowerConnector;
        this.maxTdp = maxTdp;
        this.maxNoiseLevel = maxNoiseLevel;
        this.cpuFanCount = cpuFanCount;
    }
}
