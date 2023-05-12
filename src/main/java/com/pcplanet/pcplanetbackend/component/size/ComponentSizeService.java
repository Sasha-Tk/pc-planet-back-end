package com.pcplanet.pcplanetbackend.component.size;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentSizeService {
    private final ComponentSizeRepository componentSizeRepository;

    public ComponentSize createComponentSizeOrGetExisting(List<Float> widthDepthHeight) {
        Assert.state(widthDepthHeight.size() >= 2, "Incorrect size");
        Float width = widthDepthHeight.get(0);
        Float depth = widthDepthHeight.get(1);
        Float height = widthDepthHeight.size() == 3 ? widthDepthHeight.get(2) : null;
        return componentSizeRepository
                .findByWidthAndDepthAndHeight(width, depth, height)
                .orElseGet(() -> componentSizeRepository.save(new ComponentSize(width, depth, height)));
    }
}
