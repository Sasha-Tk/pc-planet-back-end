package com.pcplanet.pcplanetbackend.component.case_fan;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilter;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.CaseFanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/case-fan")
@RequiredArgsConstructor
public class CaseFanController {
    private final CaseFanMapper caseFanMapper;
    private final CaseFanService caseFanService;

    @PostMapping("/new")
    public ResponseEntity<CaseFan> createCaseFan(
            @RequestBody CaseFanDTO caseFanDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(caseFanService.createComponent(caseFanDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCaseFan(@PathVariable Long id) {
        caseFanService.deleteComponent(id);
        return ResponseEntity.ok("Case fan deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CaseFan> updateCaseFan(@PathVariable Long id, @RequestBody CaseFanDTO caseFanDTO) {
        return ResponseEntity.ok(caseFanService.updateComponent(id, caseFanDTO, caseFanMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<CaseFan>> getAllCaseFansByParameters(
            @RequestBody(required = false) CaseFanFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(caseFanService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, CaseFan.class));
    }

    @GetMapping
    public ResponseEntity<List<CaseFan>> getAllCaseFans() {
        return ResponseEntity.ok(caseFanService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllCaseFanFilters() {
        return ResponseEntity.ok(caseFanService.getAllComponentFilters(CaseFan.class, CaseFanFilter.class));
    }
}
