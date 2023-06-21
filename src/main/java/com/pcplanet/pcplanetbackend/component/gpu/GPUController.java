package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilter;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.GPUFilterMapper;
import com.pcplanet.pcplanetbackend.component.mapper.GPUMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/gpu")
@RequiredArgsConstructor
public class GPUController {
    private final GPUMapper gpuMapper;
    private final GPUFilterMapper gpuFilterMapper;
    private final GPUService gpuService;
    private final GPUResponseMapper gpuResponseMapper;

    @PostMapping("/new")
    public ResponseEntity<GPU> createGPU(
            @RequestBody GPUDTO gpuDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(gpuService.createComponent(gpuDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGPU(@PathVariable Long id) {
        gpuService.deleteComponent(id);
        return ResponseEntity.ok("GPU deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GPU> updateGPU(@PathVariable Long id, @RequestBody GPUDTO gpuDTO) {
        return ResponseEntity.ok(gpuService.updateComponent(id, gpuDTO, gpuMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<GPU>> getAllGPUByParameters(
            @RequestBody(required = false) GPUFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(gpuService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, GPU.class));
    }

    @GetMapping
    public ResponseEntity<List<GPU>> getAllGPU() {
        return ResponseEntity.ok(gpuService.getAllComponents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GPUResponseDTO> getGPUById(@PathVariable Long id) {
        return ResponseEntity.ok(gpuResponseMapper.mapToDTO(gpuService.findComponentById(id)));
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllGPUFilters() {
        return ResponseEntity.ok(gpuService.getAllComponentFilters(GPU.class, GPUFilter.class));
    }
}
