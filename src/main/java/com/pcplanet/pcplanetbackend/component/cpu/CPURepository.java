package com.pcplanet.pcplanetbackend.component.cpu;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPURepository extends ComponentRepository<CPU>, JpaRepository<CPU, Long> {
}
