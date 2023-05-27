package com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector;

import com.pcplanet.pcplanetbackend.exception.component_exception.gpu_exception.NoSuchOutputInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FanPowerConnectorService {
    private final FanPowerConnectorRepository fanPowerConnectorRepository;

    public boolean caseFanPowerConnectorAlreadyExist(String powerConnectorName) {
        return fanPowerConnectorRepository.existsByPowerConnectorName(powerConnectorName);
    }

    public FanPowerConnector createCaseFanPowerConnector(String powerConnectorName) {
        return fanPowerConnectorRepository.save(new FanPowerConnector(powerConnectorName));
    }

    //TODO: create NoSuchCaseFanPowerConnector
    public FanPowerConnector findPowerConnectorByPowerConnectorName(String powerConnectorName) {
        return fanPowerConnectorRepository
                .findByPowerConnectorName(powerConnectorName)
                .orElseThrow(() -> new NoSuchOutputInterface("No case fan power connector with this name"));
    }

    public FanPowerConnector createPowerConnectorOrGetExisting(String powerConnectorName) {
        return caseFanPowerConnectorAlreadyExist(powerConnectorName) ?
                findPowerConnectorByPowerConnectorName(powerConnectorName) :
                createCaseFanPowerConnector(powerConnectorName);
    }

    public List<FanPowerConnector> createPowerConnectorsOrGetExisting(List<String> powerConnectorNames) {
        List<FanPowerConnector> createdPowerConnectors = new ArrayList<>();
        powerConnectorNames.forEach(powerConnectorName ->
                createdPowerConnectors.add(caseFanPowerConnectorAlreadyExist(powerConnectorName) ?
                        findPowerConnectorByPowerConnectorName(powerConnectorName) :
                        createCaseFanPowerConnector(powerConnectorName)));
        return createdPowerConnectors;
    }
}
