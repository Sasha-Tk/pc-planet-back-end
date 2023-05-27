package com.pcplanet.pcplanetbackend.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ComponentRepository<C>{
    Optional<C> findBySku(String sku);

    C save(C component);


    List<C> findAll();

    void delete(C componentById);

    Optional<C> findById(Long id);
}
