package com.pcplanet.pcplanetbackend.component.motherboard;

import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Motherboard extends Component {
    @ManyToOne
    private Socket socket;
    private String chipset;
    @ManyToOne
    private MotherboardFormFactor formFactor;
    private String memoryType;
    private Short memorySlotsAmount;
    private Short maxRamAmount;
    private Short sataConnectorsAmount;
    private Short m2ConnectorsAmount;

    public Motherboard(String name,
                       String sku,
                       Vendor vendor,
                       String imageURL,
                       Socket socket,
                       String chipset,
                       MotherboardFormFactor formFactor,
                       String memoryType,
                       Short memorySlotsAmount,
                       Short maxRamAmount,
                       Short sataConnectorsAmount,
                       Short m2ConnectorsAmount,
                       Float width,
                       Float depth,
                       Float height) {
        super(name, sku, vendor, width, depth, height, imageURL);
        this.socket = socket;
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.memoryType = memoryType;
        this.memorySlotsAmount = memorySlotsAmount;
        this.maxRamAmount = maxRamAmount;
        this.sataConnectorsAmount = sataConnectorsAmount;
        this.m2ConnectorsAmount = m2ConnectorsAmount;
    }
}
