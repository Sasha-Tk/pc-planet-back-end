package com.pcplanet.pcplanetbackend.component.hdd;

import com.pcplanet.pcplanetbackend.component.ComponentListResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.hdd.filter.HDDFilter;
import com.pcplanet.pcplanetbackend.component.hdd.filter.HDDFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.HDDFilterMapper;
import com.pcplanet.pcplanetbackend.component.mapper.HDDMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/components/hdd")
@RequiredArgsConstructor
public class HDDController {
    private final HDDMapper hddMapper;
    private final HDDFilterMapper hddFilterMapper;
    private final HDDService hddService;

    @PostMapping("/new")
    public ResponseEntity<HDD> createHDD(
            @RequestBody HDDDTO hddDTO,
            @RequestParam String storeName,
            @RequestParam Integer price
    ) {
        return ResponseEntity.ok(hddService.createComponent(hddDTO, storeName, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHDD(@PathVariable Long id) {
        hddService.deleteComponent(id);
        return ResponseEntity.ok("HDD deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HDD> updateHDD(@PathVariable Long id, @RequestBody HDDDTO hddDTO) {
        return ResponseEntity.ok(hddService.updateComponent(id, hddDTO, hddMapper));
    }

    @PostMapping
    public ResponseEntity<ComponentListResponse<HDD>> getAllHDDByParameters(
            @RequestBody(required = false) HDDFilterDTO filter,
            @RequestParam Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Optional<String> sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortingOrder

    ) {
        return ResponseEntity.ok(hddService.getAllComponents(filter, page - 1, pageSize, sortBy, sortingOrder, HDD.class));
    }

    @GetMapping
    public ResponseEntity<List<HDD>> getAllHDD() {
        return ResponseEntity.ok(hddService.getAllComponents());
    }

    @GetMapping("/filters")
    public ResponseEntity<ComponentFilterDTOResponse> getAllHDDFilters() {
        return ResponseEntity.ok(hddService.getAllComponentFilters(HDD.class, HDDFilter.class));
    }
}
