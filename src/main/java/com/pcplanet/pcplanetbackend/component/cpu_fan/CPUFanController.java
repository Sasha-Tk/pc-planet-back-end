package com.pcplanet.pcplanetbackend.component.cpu_fan;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilter;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.CPUFanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/cpu-fan")
@RequiredArgsConstructor
public class CPUFanController {
    private final CPUFanMapper cpuFanMapper;
    private final CPUFanService cpuFanService;

    @PostMapping("/new")
    public ResponseEntity<CPUFan> createCPUFan(
            @RequestBody CPUFanDTO cpuFanDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(cpuFanService.createComponent(cpuFanDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCPUFan(@PathVariable Long id) {
        cpuFanService.deleteComponent(id);
        return ResponseEntity.ok("CPU fan deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CPUFan> updateCPUFan(@PathVariable Long id, @RequestBody CPUFanDTO cpuFanDTO) {
        return ResponseEntity.ok(cpuFanService.updateComponent(id, cpuFanDTO, cpuFanMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<CPUFan>> getAllCPUFansByParameters(
            @RequestBody(required = false) CPUFanFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(cpuFanService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, CPUFan.class));
    }

    @GetMapping
    public ResponseEntity<List<CPUFan>> getAllCPUFans() {
        return ResponseEntity.ok(cpuFanService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllCPUFanFilters() {
        return ResponseEntity.ok(cpuFanService.getAllComponentFilters(CPUFan.class, CPUFanFilter.class));
    }
}
