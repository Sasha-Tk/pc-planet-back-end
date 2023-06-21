package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnector;
import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnectorService;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilter;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CaseFanFilterMapper implements Mapper<CaseFanFilterDTO, CaseFanFilter>{
    private final VendorService vendorService;
    private final FanPowerConnectorService fanPowerConnectorService;

    @Override
    public CaseFanFilter mapToEntity(CaseFanFilterDTO caseFanFilterDTO) {
        if (caseFanFilterDTO == null) {
            caseFanFilterDTO = new CaseFanFilterDTO();
        }
        return new CaseFanFilter(
                caseFanFilterDTO.getVendor() == null ?
                        null :
                        caseFanFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                caseFanFilterDTO.getFanSize(),
                caseFanFilterDTO.getFanPowerConnector() == null ?
                        null :
                        caseFanFilterDTO.getFanPowerConnector()
                                .stream()
                                .map(fanPowerConnectorService::findPowerConnectorByPowerConnectorName)
                                .toList(),
                caseFanFilterDTO.getRpm(),
                caseFanFilterDTO.getMaxNoiseLevel(),
                caseFanFilterDTO.getCaseFanCount()
        );
    }

    @Override
    public CaseFanFilterDTO mapToDTO(CaseFanFilter caseFanFilter) {
        return new CaseFanFilterDTO(
                caseFanFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                caseFanFilter.getFanSize(),
                caseFanFilter.getFanPowerConnector()
                        .stream()
                        .map(FanPowerConnector::getPowerConnectorName)
                        .toList(),
                caseFanFilter.getRpm(),
                caseFanFilter.getMaxNoiseLevel(),
                caseFanFilter.getCaseFanCount()
        );
    }
}
