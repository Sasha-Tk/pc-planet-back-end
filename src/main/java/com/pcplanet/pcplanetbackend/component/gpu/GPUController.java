package com.pcplanet.pcplanetbackend.component.gpu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/components/gpu")
@RequiredArgsConstructor
public class GPUController {
    private final GPUService gpuService;

    @PostMapping()
    public ResponseEntity<GPU> createGPU(@RequestBody GPUDTO gpudto) {
        return ResponseEntity.ok(gpuService.createGpu(gpudto));
    }
}
