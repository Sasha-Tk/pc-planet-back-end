package com.pcplanet.pcplanetbackend.component;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ComponentRepository<C> {
    Optional<C> findBySku(String sku);

    C save(C component);

    List<C> findAll();

    void delete(C componentById);

    Optional<C> findById(Long id);
}
