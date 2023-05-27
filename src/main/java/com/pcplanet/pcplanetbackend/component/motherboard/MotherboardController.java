package com.pcplanet.pcplanetbackend.component.motherboard;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.MotherboardFilterMapper;
import com.pcplanet.pcplanetbackend.component.mapper.MotherboardMapper;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilter;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilterDTO;
import com.pcplanet.pcplanetbackend.component.psu.PSU;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilter;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/motherboard")
@RequiredArgsConstructor
public class MotherboardController {
    private final MotherboardMapper motherboardMapper;
    private final MotherboardFilterMapper motherboardFilterMapper;
    private final MotherboardService motherboardService;

    @PostMapping("/new")
    public ResponseEntity<Motherboard> createMotherboard(
            @RequestBody MotherboardDTO motherboardDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(motherboardService.createComponent(motherboardDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMotherboard(@PathVariable Long id) {
        motherboardService.deleteComponent(id);
        return ResponseEntity.ok("Motherboard deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Motherboard> updateMotherboard(@PathVariable Long id, @RequestBody MotherboardDTO motherboardDTO) {
        return ResponseEntity.ok(motherboardService.updateComponent(id, motherboardDTO, motherboardMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<Motherboard>> getAllMotherboardsByParameters(
            @RequestBody(required = false) MotherboardFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(motherboardService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, Motherboard.class));
    }

    @GetMapping
    public ResponseEntity<List<Motherboard>> getAllMotherboards() {
        return ResponseEntity.ok(motherboardService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllMotherboardFilters() {
        return ResponseEntity.ok(motherboardService.getAllComponentFilters(Motherboard.class, MotherboardFilter.class));
    }
}
