package com.pcplanet.pcplanetbackend.component.case_fan;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class CaseFan extends Component {
    private Short fanSize;
    @ManyToMany
    @JoinTable(name = "case_fan_with_power_connector")
    private List<FanPowerConnector> fanPowerConnector;
    private Short rpm;
    private Float maxNoiseLevel;
    private Short caseFanCount;

    public CaseFan(String name,
                   String sku,
                   Vendor vendor,
                   String imageURL,
                   Short fanSize,
                   List<FanPowerConnector> fanPowerConnector,
                   Short rpm,
                   Float maxNoiseLevel,
                   Short caseFanCount,
                   Float width,
                   Float depth,
                   Float height) {
        super(name, sku, vendor, width, depth, height, imageURL);
        this.fanSize = fanSize;
        this.fanPowerConnector = fanPowerConnector;
        this.rpm = rpm;
        this.maxNoiseLevel = maxNoiseLevel;
        this.caseFanCount = caseFanCount;
    }
}
