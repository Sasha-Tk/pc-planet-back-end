package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class GPUFilter {
    private List<String> sku;
    private List<String> chip;
    private List<Vendor> vendor;
}
