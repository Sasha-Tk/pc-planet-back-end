package com.pcplanet.pcplanetbackend.component.hdd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HDDResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<String> driveCapacity;
    private List<String> hddConnectionInterface;
    private List<String> formFactor;
    private List<Short> dataTransferSpeed;
    private List<Short> rotationSpeed;
    private String imageURL;
    private Integer lowerPrice;
}
