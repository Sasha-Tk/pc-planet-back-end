package com.pcplanet.pcplanetbackend.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentBasicRepository extends JpaRepository<Component, Long> {
    List<Component> findAllByComponentNameLikeIgnoreCaseOrSkuIgnoreCase(String componentName, String sku);
}
