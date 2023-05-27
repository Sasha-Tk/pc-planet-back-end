package com.pcplanet.pcplanetbackend.component.ram;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class RAM extends Component {
    private String memoryType;
    private Short memoryAmount;
    private Short memoryFrequency;
    private Short barsNumber;
    private String formFactor;

    public RAM(String name,
               String sku,
               Vendor vendor,
               String imageURL,
               String memoryType,
               Short memoryAmount,
               Short memoryFrequency,
               Short barsNumber,
               String formFactor,
               Float width,
               Float depth,
               Float height) {
        super(name, sku, vendor, width, depth, height, imageURL);
        this.memoryType = memoryType;
        this.memoryAmount = memoryAmount;
        this.memoryFrequency = memoryFrequency;
        this.barsNumber = barsNumber;
        this.formFactor = formFactor;
    }
}
