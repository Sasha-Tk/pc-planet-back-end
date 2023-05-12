package com.pcplanet.pcplanetbackend.component.gpu.output_interface;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "gpu_output_interface")
public class GPUOutputInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String interfaceName;
    public GPUOutputInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
