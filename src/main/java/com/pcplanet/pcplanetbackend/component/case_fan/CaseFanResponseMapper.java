package com.pcplanet.pcplanetbackend.component.case_fan;

import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseFanResponseMapper {
    public CaseFanResponseDTO mapToDTO(CaseFan caseFan) {
        return new CaseFanResponseDTO(
                caseFan.getId(),
                caseFan.getSku(),
                caseFan.getComponentName(),
                List.of(caseFan.getVendor().getVendorName()),
                List.of(caseFan.getFanSize()),
                caseFan.getFanPowerConnector().stream().map(FanPowerConnector::getPowerConnectorName).toList(),
                List.of(caseFan.getRpm()),
                List.of(caseFan.getMaxNoiseLevel()),
                List.of(caseFan.getCaseFanCount()),
                List.of(caseFan.getWidth(), caseFan.getDepth(), caseFan.getHeight()),
                caseFan.getImageURL(),
                caseFan.getLowerPrice()
        );
    }
}
