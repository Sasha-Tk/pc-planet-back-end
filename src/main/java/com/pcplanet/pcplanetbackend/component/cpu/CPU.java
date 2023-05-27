package com.pcplanet.pcplanetbackend.component.cpu;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class CPU extends Component {
    private String cpuFamily;
    @ManyToOne
    private Socket socket;
    private Short coreCount;
    private Short threadCount;
    private Short cpuFrequency;
    private String memoryType;
    private Short tdpPower;

    public CPU(String name,
               String sku,
               Vendor vendor,
               String imageURL,
               String cpuFamily,
               Socket socket,
               Short coreCount,
               Short threadCount,
               Short cpuFrequency,
               String memoryType,
               Short tdpPower) {
        super(name, sku, vendor, null,null,null, imageURL);
        this.cpuFamily = cpuFamily;
        this.socket = socket;
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.cpuFrequency = cpuFrequency;
        this.memoryType = memoryType;
        this.tdpPower = tdpPower;
    }
}
