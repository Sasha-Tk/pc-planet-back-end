package com.pcplanet.pcplanetbackend.component.ram;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.RAMMapper;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilter;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/ram")
@RequiredArgsConstructor
public class RAMController {
    private final RAMMapper ramMapper;
    private final RAMService ramService;

    @PostMapping("/new")
    public ResponseEntity<RAM> createRAM(
            @RequestBody RAMDTO ramDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(ramService.createComponent(ramDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRAM(@PathVariable Long id) {
        ramService.deleteComponent(id);
        return ResponseEntity.ok("RAM deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RAM> updateRAM(@PathVariable Long id, @RequestBody RAMDTO ramDTO) {
        return ResponseEntity.ok(ramService.updateComponent(id, ramDTO, ramMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<RAM>> getAllRAMByParameters(
            @RequestBody(required = false) RAMFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(ramService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, RAM.class));
    }

    @GetMapping
    public ResponseEntity<List<RAM>> getAllRAM() {
        return ResponseEntity.ok(ramService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllRAMFilters() {
        return ResponseEntity.ok(ramService.getAllComponentFilters(RAM.class, RAMFilter.class));
    }
}
