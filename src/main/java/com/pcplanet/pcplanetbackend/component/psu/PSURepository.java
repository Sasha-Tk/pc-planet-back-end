package com.pcplanet.pcplanetbackend.component.psu;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PSURepository extends ComponentRepository<PSU>, JpaRepository<PSU,Long> {
}
