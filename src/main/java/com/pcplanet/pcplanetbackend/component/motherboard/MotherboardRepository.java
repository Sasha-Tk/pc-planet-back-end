package com.pcplanet.pcplanetbackend.component.motherboard;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends ComponentRepository<Motherboard>, JpaRepository<Motherboard, Long> {
}
