package com.pcplanet.pcplanetbackend.component.gpu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GPUFilterDTO {
    private List<String> sku;
    private List<String> chip;
    private List<String> vendor;
}
