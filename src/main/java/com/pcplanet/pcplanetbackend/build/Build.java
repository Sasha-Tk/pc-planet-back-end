package com.pcplanet.pcplanetbackend.build;

import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCase;
import com.pcplanet.pcplanetbackend.component.cpu.CPU;
import com.pcplanet.pcplanetbackend.component.gpu.GPU;
import com.pcplanet.pcplanetbackend.component.motherboard.Motherboard;
import com.pcplanet.pcplanetbackend.component.psu.PSU;
import com.pcplanet.pcplanetbackend.component.ram.RAM;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Build {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private GPU gpu;
    @ManyToOne(cascade = CascadeType.ALL)
    private PSU psu;
    @ManyToOne(cascade = CascadeType.ALL)
    private Motherboard motherboard;
    @ManyToOne(cascade = CascadeType.ALL)
    private CPU cpu;
    @ManyToOne(cascade = CascadeType.ALL)
    private RAM ram;
    @ManyToOne(cascade = CascadeType.ALL)
    private ComputerCase computerCase;

    public Build(GPU gpu, PSU psu, Motherboard motherboard, CPU cpu, RAM ram, ComputerCase computerCase) {
        this.gpu = gpu;
        this.psu = psu;
        this.motherboard = motherboard;
        this.cpu = cpu;
        this.ram = ram;
        this.computerCase = computerCase;
    }
}
