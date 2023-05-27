package com.pcplanet.pcplanetbackend.component.psu;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class PSU extends Component {
    private Short power;
    private String cpuPowerConnector;
    private Short numberOfAdditionalPowerConnectorsForGpu;
    private Short cooling;
    private String typeOfAdditionalPowerConnectorsForGpu;
    private Short numberOfSataConnectors;

    public PSU(String name,
               String sku,
               Vendor vendor,
               String imageHRef,
               Short power,
               String cpuPowerConnector,
               Short numberOfAdditionalPowerConnectorsForGpu,
               Short cooling,
               String typeOfAdditionalPowerConnectorsForGpu,
               Short numberOfSataConnectors,
               Float width,
               Float depth,
               Float height) {
        super(name, sku, vendor, width, depth, height, imageHRef);
        this.power = power;
        this.cpuPowerConnector = cpuPowerConnector;
        this.numberOfAdditionalPowerConnectorsForGpu = numberOfAdditionalPowerConnectorsForGpu;
        this.cooling = cooling;
        this.typeOfAdditionalPowerConnectorsForGpu = typeOfAdditionalPowerConnectorsForGpu;
        this.numberOfSataConnectors = numberOfSataConnectors;
    }
}
