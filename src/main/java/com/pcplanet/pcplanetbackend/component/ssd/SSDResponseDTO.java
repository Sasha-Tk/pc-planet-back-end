package com.pcplanet.pcplanetbackend.component.ssd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SSDResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> driveCapacity;
    private List<String> sddConnectionInterface;
    private List<String> formFactor;
    private List<Short> readSpeed;
    private List<Short> writeSpeed;
    private String imageURL;
    private Integer lowerPrice;
}
