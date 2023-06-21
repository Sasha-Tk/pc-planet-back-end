package com.pcplanet.pcplanetbackend.component.computer_case;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilter;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.ComputerCaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/case")
@RequiredArgsConstructor
public class ComputerCaseController {
    private final ComputerCaseMapper computerCaseMapper;
    private final ComputerCaseService computerCaseService;
    private final ComputerCaseResponseMapper computerCaseResponseMapper;

    @PostMapping("/new")
    public ResponseEntity<ComputerCase> createComputerCase(
            @RequestBody ComputerCaseDTO computerCaseDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(computerCaseService.createComponent(computerCaseDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComputerCase(@PathVariable Long id) {
        computerCaseService.deleteComponent(id);
        return ResponseEntity.ok("Case deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ComputerCase> updateComputerCase(@PathVariable Long id, @RequestBody ComputerCaseDTO computerCaseDTO) {
        return ResponseEntity.ok(computerCaseService.updateComponent(id, computerCaseDTO, computerCaseMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<ComputerCase>> getAllComputerCasesByParameters(
            @RequestBody(required = false) ComputerCaseFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(computerCaseService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, ComputerCase.class));
    }

    @GetMapping
    public ResponseEntity<List<ComputerCase>> getAllComputerCases() {
        return ResponseEntity.ok(computerCaseService.getAllComponents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerCaseResponseDTO> getComputerCaseById(@PathVariable Long id){
        return ResponseEntity.ok(computerCaseResponseMapper.mapToDTO(computerCaseService.findComponentById(id)));
    }

//    @GetMapping("/{sku}")
//    public ResponseEntity<ComputerCaseResponseDTO> getComputerCaseBySku(@PathVariable String sku){
//        return ResponseEntity.ok(computerCaseResponseMapper.mapToDTO(computerCaseService.findComponentBySku(sku)));
//    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllComputerCaseFilters() {
        return ResponseEntity.ok(computerCaseService.getAllComponentFilters(ComputerCase.class, ComputerCaseFilter.class));
    }
}
