package com.pcplanet.pcplanetbackend.component.hdd;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HDDRepository extends ComponentRepository<HDD>, JpaRepository<HDD, Long> {
}
