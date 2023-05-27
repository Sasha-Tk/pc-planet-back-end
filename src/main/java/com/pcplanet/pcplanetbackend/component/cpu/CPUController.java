package com.pcplanet.pcplanetbackend.component.cpu;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilter;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUService;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.CPUMapper;
import com.pcplanet.pcplanetbackend.component.psu.PSU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/cpu")
@RequiredArgsConstructor
public class CPUController {
    private final CPUMapper cpuMapper;
    private final CPUService cpuService;

    @PostMapping("/new")
    public ResponseEntity<CPU> createCPU(
            @RequestBody CPUDTO cpudto,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(cpuService.createComponent(cpudto, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCPU(@PathVariable Long id) {
        cpuService.deleteComponent(id);
        return ResponseEntity.ok("CPU deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CPU> updateCPU(@PathVariable Long id, @RequestBody CPUDTO cpudto) {
        return ResponseEntity.ok(cpuService.updateComponent(id, cpudto, cpuMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<CPU>> getAllCPUByParameters(
            @RequestBody(required = false) CPUFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(cpuService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, CPU.class));
    }

    @GetMapping
    public ResponseEntity<List<CPU>> getAllCPU() {
        return ResponseEntity.ok(cpuService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllCPUFilters() {
        return ResponseEntity.ok(cpuService.getAllComponentFilters(CPU.class, CPUFilter.class));
    }
}
