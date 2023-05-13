package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/components/gpu")
@RequiredArgsConstructor
public class GPUController {
    private final GPUService gpuService;

    @PostMapping
    public ResponseEntity<GPU> createGPU(@RequestBody GPUDTO gpudto) {
        return ResponseEntity.ok(gpuService.createGPU(gpudto));
    }

    @DeleteMapping("/byID/{id}")
    public ResponseEntity<String> deleteGPU(@PathVariable Long id) {
        gpuService.deleteGPU(id);
        return ResponseEntity.ok("GPU deleted");
    }

    @PatchMapping("/byID/{id}")
    public ResponseEntity<GPU> updateGPU(@PathVariable Long id, @RequestBody GPUDTO gpuDTO) {
        return ResponseEntity.ok(gpuService.updateGPU(id, gpuDTO));
    }

    @GetMapping()
    public ResponseEntity<List<GPU>> getAllGPU() {
        return ResponseEntity.ok(gpuService.getAllGPU());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<GPU>> getAllGPU(@RequestBody GPUFilterDTO gpuFilterDTO) {
        return ResponseEntity.ok(gpuService.findGPUByFilterParameters(gpuFilterDTO));
    }

    @GetMapping("/filters")
    public ResponseEntity<GPUFilterDTO> getAllGPUFilters() {
        return ResponseEntity.ok(gpuService.getAllGPUFilters());
    }
}
