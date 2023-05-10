package com.pcplanet.pcplanetbackend.component.cpu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPURepository extends JpaRepository<CPU,Long> {
}
