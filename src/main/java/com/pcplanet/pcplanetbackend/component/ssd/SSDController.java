package com.pcplanet.pcplanetbackend.component.ssd;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.SSDFilterMapper;
import com.pcplanet.pcplanetbackend.component.mapper.SSDMapper;
import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilter;
import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/ssd")
@RequiredArgsConstructor
public class SSDController {
    private final SSDMapper ssdMapper;
    private final SSDFilterMapper ssdFilterMapper;
    private final SSDService ssdService;

    @PostMapping("/new")
    public ResponseEntity<SSD> createSSD(
            @RequestBody SSDDTO ssdDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(ssdService.createComponent(ssdDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSSD(@PathVariable Long id) {
        ssdService.deleteComponent(id);
        return ResponseEntity.ok("SSD deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SSD> updateSSD(@PathVariable Long id, @RequestBody SSDDTO ssdDTO) {
        return ResponseEntity.ok(ssdService.updateComponent(id, ssdDTO, ssdMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<SSD>> getAllSSDByParameters(
            @RequestBody(required = false) SSDFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(ssdService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, SSD.class));
    }

    @GetMapping
    public ResponseEntity<List<SSD>> getAllSSD() {
        return ResponseEntity.ok(ssdService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllSSDFilters() {
        return ResponseEntity.ok(ssdService.getAllComponentFilters(SSD.class, SSDFilter.class));
    }
}
