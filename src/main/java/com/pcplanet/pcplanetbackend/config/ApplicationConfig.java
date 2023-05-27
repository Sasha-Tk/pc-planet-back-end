package com.pcplanet.pcplanetbackend.config;

import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final VendorService vendorService;
    private final GPUOutputInterfaceService gpuOutputInterfaceService;


}
