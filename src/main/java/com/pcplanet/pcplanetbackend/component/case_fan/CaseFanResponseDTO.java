package com.pcplanet.pcplanetbackend.component.case_fan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CaseFanResponseDTO {
    private Long id;
    private String sku;
    private String componentName;
    private List<String> vendor;
    private List<Short> fanSize;
    private List<String> fanPowerConnector;
    private List<Short> rpm;
    private List<Float> maxNoiseLevel;
    private List<Short> caseFanCount;
    private List<Float> size;
    private String imageURL;
    private Integer lowerPrice;
}
