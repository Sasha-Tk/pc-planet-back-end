package com.pcplanet.pcplanetbackend.component.gpu.output_interface;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GPUOutputInterfaceRepository extends JpaRepository<GPUOutputInterface, Short> {
    boolean existsByInterfaceName(String interfaceName);

    Optional<GPUOutputInterface> findByInterfaceName(String gpuOutputInterfaceName);
}
