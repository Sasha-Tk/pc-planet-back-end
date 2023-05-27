package com.pcplanet.pcplanetbackend.component.cpu_fan;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPUFanRepository extends ComponentRepository<CPUFan>, JpaRepository<CPUFan, Long> {
}
