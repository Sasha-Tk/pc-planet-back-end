package com.pcplanet.pcplanetbackend.build;

import com.pcplanet.pcplanetbackend.component.case_fan.CaseFan;
import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCase;
import com.pcplanet.pcplanetbackend.component.cpu.CPU;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFan;
import com.pcplanet.pcplanetbackend.component.gpu.GPU;
import com.pcplanet.pcplanetbackend.component.hdd.HDD;
import com.pcplanet.pcplanetbackend.component.motherboard.Motherboard;
import com.pcplanet.pcplanetbackend.component.psu.PSU;
import com.pcplanet.pcplanetbackend.component.ram.RAM;
import com.pcplanet.pcplanetbackend.component.ssd.SSD;
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
    @ManyToOne
    private Motherboard motherboard;
    @ManyToOne
    private CPU cpu;
    @ManyToOne
    private RAM ram;
    @ManyToOne
    private GPU gpu;
    @ManyToOne
    private PSU psu;
    @ManyToOne
    private ComputerCase computerCase;
    @ManyToOne
    private SSD ssd;
    @ManyToOne
    private HDD hdd;
    @ManyToOne
    private CPUFan cpuFan;
    @ManyToOne
    private CaseFan caseFan;

    @Transient
    private Integer totalLowerPrice;

    public Build(Motherboard motherboard,
                 CPU cpu,
                 RAM ram,
                 GPU gpu,
                 PSU psu,
                 ComputerCase computerCase,
                 SSD ssd,
                 HDD hdd,
                 CPUFan cpuFan,
                 CaseFan caseFan) {
        this.motherboard = motherboard;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.psu = psu;
        this.computerCase = computerCase;
        this.ssd = ssd;
        this.hdd = hdd;
        this.cpuFan = cpuFan;
        this.caseFan = caseFan;
    }

    public Integer getTotalLowerPrice() {
        Integer totalPrice = 0;
        if (motherboard != null) {
            totalPrice += motherboard.getLowerPrice();
        }
        if (cpu != null) {
            totalPrice += cpu.getLowerPrice();
        }
        if (ram != null) {
            totalPrice += ram.getLowerPrice();
        }
        if (gpu != null) {
            totalPrice += gpu.getLowerPrice();
        }
        if (psu != null) {
            totalPrice += psu.getLowerPrice();
        }
        if (computerCase != null) {
            totalPrice += computerCase.getLowerPrice();
        }
        if (ssd != null) {
            totalPrice += ssd.getLowerPrice();
        }
        if (hdd != null) {
            totalPrice += hdd.getLowerPrice();
        }
        if (cpuFan != null) {
            totalPrice += cpuFan.getLowerPrice();
        }
        if (caseFan != null) {
            totalPrice += caseFan.getLowerPrice();
        }
        return totalPrice;
    }
}
