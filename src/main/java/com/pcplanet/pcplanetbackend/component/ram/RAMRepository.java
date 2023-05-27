package com.pcplanet.pcplanetbackend.component.ram;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAMRepository extends ComponentRepository<RAM>, JpaRepository<RAM, Long> {
}
