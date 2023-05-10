package com.pcplanet.pcplanetbackend.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
//    List<Product> findAllByNameLikeIgnoreCaseAndSkuLikeIgnoreCase
}
