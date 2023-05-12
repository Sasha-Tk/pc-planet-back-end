package com.pcplanet.pcplanetbackend.component.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentSizeRepository extends JpaRepository<ComponentSize, Long> {
    Boolean existsComponentSizeByWidthAndDepthAndHeight(Float width, Float depth, Float height);
    Optional<ComponentSize> findByWidthAndDepthAndHeight(Float width, Float depth, Float height);
}
