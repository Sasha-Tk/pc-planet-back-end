package com.pcplanet.pcplanetbackend.component;

import com.pcplanet.pcplanetbackend.component.price_history.PriceHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/components")
@RequiredArgsConstructor
public class ComponentController {
    private final ComponentBasicService componentBasicService;

    @GetMapping("/{id}/offers")
    public ResponseEntity<List<PriceHistory>> getCurrentOffers(@PathVariable Long id) {
        return ResponseEntity.ok(componentBasicService.getCurrentOffers(id));
    }


    @GetMapping("/search/{value}")
    public ResponseEntity<List<Component>> getAllComponentsBySearchValue(@PathVariable String value){
        return ResponseEntity.ok(componentBasicService.getAllComponentsBySearchValue(value));
    }
}
