package com.pcplanet.pcplanetbackend.component.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<DTO, Entity> {
    default Entity mapToEntity(DTO dto) {
        return null;
    }

    default DTO mapToDTO(Entity entity) {
        return null;
    }
}
