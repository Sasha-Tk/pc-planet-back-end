package com.pcplanet.pcplanetbackend.component.hdd;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class HDD extends Component {
    private String driveCapacity;
    private String hddConnectionInterface;
    private String formFactor;
    private Short dataTransferSpeed;
    private Short rotationSpeed;

    public HDD(String name,
               String sku,
               Vendor vendor,
               String imageURL,
               String driveCapacity,
               String hddConnectionInterface,
               String formFactor,
               Short dataTransferSpeed,
               Short rotationSpeed,
               Float width,
               Float depth,
               Float height) {
        super(name, sku, vendor, width, depth, height, imageURL);
        this.driveCapacity = driveCapacity;
        this.hddConnectionInterface = hddConnectionInterface;
        this.formFactor = formFactor;
        this.dataTransferSpeed = dataTransferSpeed;
        this.rotationSpeed = rotationSpeed;
    }
}
