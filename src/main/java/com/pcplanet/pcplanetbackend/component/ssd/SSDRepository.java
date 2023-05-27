package com.pcplanet.pcplanetbackend.component.ssd;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SSDRepository extends ComponentRepository<SSD>, JpaRepository<SSD, Long> {
}
