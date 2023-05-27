package com.pcplanet.pcplanetbackend.component.motherboard;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MotherboardDTO extends ComponentDTO {
    private String socket;
    private String chipset;
    private String formFactor;
    private String memoryType;
    private Short memorySlotsAmount;
    private Short maxRamAmount;
    Short sataConnectorsAmount;
    Short m2ConnectorsAmount;
}
