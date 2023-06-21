package com.pcplanet.pcplanetbackend.component.computer_case;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.ComponentType;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
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
public class ComputerCase extends Component {
    @ManyToMany
    @JoinTable(name = "computer_case_with_form_factor")
    private List<MotherboardFormFactor> motherboardFormFactor;
    private Float maxCpuFanHeight;
    private Float maxGpuWidth;
    private Short caseFanCount;

    public ComputerCase(String name,
                        String sku,
                        Vendor vendor,
                        String imageURL,
                        List<MotherboardFormFactor> motherboardFormFactor,
                        Float maxCpuFanHeight,
                        Float maxGpuWidth,
                        Short caseFanCount,
                        Float width,
                        Float depth,
                        Float height) {
        super(ComponentType.CASE, name, sku, vendor, width, depth, height, imageURL);
        this.motherboardFormFactor = motherboardFormFactor;
        this.maxCpuFanHeight = maxCpuFanHeight;
        this.maxGpuWidth = maxGpuWidth;
        this.caseFanCount = caseFanCount;
    }
}
