package com.pcplanet.pcplanetbackend.component.gpu.output_interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GPUOutputInterfaceRepository extends JpaRepository<GPUOutputInterface, Short> {
    boolean existsByInterfaceName(String interfaceName);
    Optional<GPUOutputInterface> findByInterfaceName(String gpuOutputInterfaceName);
}
