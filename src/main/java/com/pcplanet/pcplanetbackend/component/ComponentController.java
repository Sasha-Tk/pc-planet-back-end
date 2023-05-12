package com.pcplanet.pcplanetbackend.component;

import com.pcplanet.pcplanetbackend.component.gpu.GPURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/components")
@RequiredArgsConstructor
public class ComponentController {
    private final GPURepository gpuRepository;
    private final ComponentRepository componentRepository;

    @GetMapping()
    public ResponseEntity<List<Component>> getGpuById() {
        return ResponseEntity.ok(componentRepository.findAll());
    }
    @GetMapping("/byID/{id}")
    public ResponseEntity<Component> getGpuById(@PathVariable Long id) {
        return ResponseEntity.ok(componentRepository.findById(id).get());
    }

    @DeleteMapping("/byID/{id}")
    public ResponseEntity<List<Component>> deleteProductById(@PathVariable Long id){
        componentRepository.deleteById(id);
        return ResponseEntity.ok(componentRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<List<Component>> createProduct(@RequestBody Component newProduct){
        componentRepository.save(newProduct);
        return ResponseEntity.ok(componentRepository.findAll());
    }
}
