package com.pcplanet.pcplanetbackend.component.motherboard;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MotherboardResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> socket;
    private List<String> chipset;
    private List<String> formFactor;
    private List<String> memoryType;
    private List<Short> memorySlotsAmount;
    private List<Short> maxRamAmount;
    private List<Short> sataConnectorsAmount;
    private List<Short> m2ConnectorsAmount;
    private String imageURL;
    private Integer lowerPrice;
}
