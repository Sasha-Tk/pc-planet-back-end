package com.pcplanet.pcplanetbackend.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;

    public Optional<Component> findBySku(String sku) {
        return componentRepository.findBySku(sku);
    }
}
