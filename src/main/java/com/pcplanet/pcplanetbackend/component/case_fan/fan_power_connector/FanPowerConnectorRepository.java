package com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FanPowerConnectorRepository extends JpaRepository<FanPowerConnector, Short> {
    boolean existsByPowerConnectorName(String powerConnectorName);

    Optional<FanPowerConnector> findByPowerConnectorName(String powerConnectorName);
}
