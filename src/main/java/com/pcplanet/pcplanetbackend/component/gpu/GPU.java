package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterface;
import com.pcplanet.pcplanetbackend.component.size.ComponentSize;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class GPU extends Component {
    private String chip;
    private Short chipFrequency;
    private Short memoryAmount;
    private String memoryType;
    private Integer memoryFrequency;
    private Integer memoryInterfaceWidth;
    private String connectionInterface;
    private String additionalPower;
    private Short recommendedPsuPower;
    @ManyToOne
    private ComponentSize size;
    @ManyToMany
    @JoinTable(name = "gpu_with_interface")
    private List<GPUOutputInterface> outputInterfaces;

    public GPU(String name,
               String sku,
               Vendor vendor,
               String chip,
               Short chipFrequency,
               Short memoryAmount,
               String memoryType,
               Integer memoryFrequency,
               Integer memoryInterfaceWidth,
               String connectionInterface,
               String additionalPower,
               Short recommendedPsuPower,
               ComponentSize size,
               List<GPUOutputInterface> outputInterfaces) {
        super(name, sku, vendor);
        this.chip = chip;
        this.chipFrequency = chipFrequency;
        this.memoryAmount = memoryAmount;
        this.memoryType = memoryType;
        this.memoryFrequency = memoryFrequency;
        this.memoryInterfaceWidth = memoryInterfaceWidth;
        this.connectionInterface = connectionInterface;
        this.additionalPower = additionalPower;
        this.recommendedPsuPower = recommendedPsuPower;
        this.size = size;
        this.outputInterfaces = outputInterfaces;
    }
}
