package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/gpu")
@RequiredArgsConstructor
public class GPUController {
    private final GPUService gpuService;

    @PostMapping("/new")
    public ResponseEntity<GPU> createGPU(
            @RequestBody GPUDTO gpuDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(gpuService.createGPU(gpuDTO, storeName, price));
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

    @PostMapping
    public ResponseEntity<ComponentListResponse<GPU>> getAllGPUByParameters(
            @RequestBody(required = false) GPUFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(gpuService.getAllGPUByParameters(filter, page - 1, pageSize, sortBy, sortingOrder));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GPU>> getAllGPU() {
        return ResponseEntity.ok(gpuService.getAllGPU());
    }


    @GetMapping("/filters")
    public ResponseEntity<GPUFilterDTOResponse> getAllGPUFilters() {
        return ResponseEntity.ok(gpuService.getAllGPUFilters());
    }
}
