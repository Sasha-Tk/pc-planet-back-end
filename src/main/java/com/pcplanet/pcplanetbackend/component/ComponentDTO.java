package com.pcplanet.pcplanetbackend.component;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public abstract class ComponentDTO {
    private String componentName;
    private String sku;
    private String vendor;
    private String imageURL;
    private List<Float> size;
}
