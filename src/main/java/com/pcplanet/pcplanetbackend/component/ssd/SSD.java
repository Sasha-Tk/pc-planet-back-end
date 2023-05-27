package com.pcplanet.pcplanetbackend.component.ssd;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class SSD extends Component {
    private String driveCapacity;
    private String sddConnectionInterface;
    private String formFactor;
    private Short readSpeed;
    private Short writeSpeed;

    public SSD(String name,
               String sku,
               Vendor vendor,
               String imageURL,
               String driveCapacity,
               String sddConnectionInterface,
               String formFactor,
               Short readSpeed,
               Short writeSpeed,
               Float width,
               Float depth,
               Float height) {
        super(name, sku, vendor, width, depth, height, imageURL);
        this.driveCapacity = driveCapacity;
        this.sddConnectionInterface = sddConnectionInterface;
        this.formFactor = formFactor;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }
}
