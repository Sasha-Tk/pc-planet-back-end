package com.pcplanet.pcplanetbackend.component.mapper;

public interface Mapper<DTO, Entity> {
    default Entity mapToEntity(DTO dto){
        return null;
    }

    default DTO mapToDTO(Entity entity){
        return null;
    }
}
