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
//    private final GPURepository gpuRepository;
//    private final ComponentR componentR;
//
//    @GetMapping()
//    public ResponseEntity<List<Component>> getGpuById() {
//        return ResponseEntity.ok(componentR.findAll());
//    }
//    @GetMapping("/byID/{id}")
//    public ResponseEntity<Component> getGpuById(@PathVariable Long id) {
//        return ResponseEntity.ok(componentR.findById(id).get());
//    }
//
//    @DeleteMapping("/byID/{id}")
//    public ResponseEntity<List<Component>> deleteProductById(@PathVariable Long id){
//        componentR.deleteById(id);
//        return ResponseEntity.ok(componentR.findAll());
//    }
//
//    @PostMapping()
//    public ResponseEntity<List<Component>> createProduct(@RequestBody Component newProduct){
//        componentR.save(newProduct);
//        return ResponseEntity.ok(componentR.findAll());
//    }
}
