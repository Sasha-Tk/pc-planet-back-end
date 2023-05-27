package com.pcplanet.pcplanetbackend.component.psu;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.PSUFilterMapper;
import com.pcplanet.pcplanetbackend.component.mapper.PSUMapper;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilter;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/psu")
@RequiredArgsConstructor
public class PSUController {
    private final PSUMapper psuMapper;
    private final PSUFilterMapper psuFilterMapper;

//    private final ComponentService<PSU, PSUDTO, PSUFilter, PSUFilterDTO> psuService;
    private final PSUService psuService;

    @PostMapping("/new")
    public ResponseEntity<PSU> createPSU(
            @RequestBody PSUDTO psuDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(psuService.createComponent(psuDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePSU(@PathVariable Long id) {
        psuService.deleteComponent(id);
        return ResponseEntity.ok("PSU deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PSU> updatePSU(@PathVariable Long id, @RequestBody PSUDTO psuDTO) {
        return ResponseEntity.ok(psuService.updateComponent(id, psuDTO, psuMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<PSU>> getAllPSUByParameters(
            @RequestBody(required = false) PSUFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(psuService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, PSU.class));
    }

    @GetMapping
    public ResponseEntity<List<PSU>> getAllPSU() {
        return ResponseEntity.ok(psuService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllPSUFilters() {
        return ResponseEntity.ok(psuService.getAllComponentFilters(PSU.class, PSUFilter.class));
    }
}
