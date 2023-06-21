package com.pcplanet.pcplanetbackend.component;

import lombok.Data;

import java.util.List;

@Data
public abstract class ComponentDTO {
    private String componentName;
    private String sku;
    private String vendor;
    private String imageURL;
    private String componentURL;
    private List<Float> size;
}
