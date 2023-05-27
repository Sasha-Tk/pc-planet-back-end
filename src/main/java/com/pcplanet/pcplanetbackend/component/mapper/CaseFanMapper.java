package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.case_fan.CaseFan;
import com.pcplanet.pcplanetbackend.component.case_fan.CaseFanDTO;
import com.pcplanet.pcplanetbackend.component.case_fan.fan_power_connector.FanPowerConnectorService;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CaseFanMapper implements Mapper<CaseFanDTO, CaseFan> {
    private final VendorService vendorService;
    private final FanPowerConnectorService fanPowerConnectorService;

    @Override
    public CaseFan mapToEntity(CaseFanDTO caseFanDTO) {
        return new CaseFan(
                caseFanDTO.getComponentName(),
                caseFanDTO.getSku(),
                caseFanDTO.getVendor() == null ?
                        null :
                        vendorService
                                .createVendorOrGetExisting(
                                        caseFanDTO.getVendor()),
                caseFanDTO.getImageURL(),
                caseFanDTO.getFanSize(),
                caseFanDTO.getFanPowerConnector() == null ?
                        null :
                        fanPowerConnectorService.createPowerConnectorsOrGetExisting(caseFanDTO.getFanPowerConnector()),
                caseFanDTO.getRpm(),
                caseFanDTO.getMaxNoiseLevel(),
                caseFanDTO.getCaseFanCount(),
                caseFanDTO.getSize().get(0),
                caseFanDTO.getSize().get(1),
                caseFanDTO.getSize().size() >= 3 ? caseFanDTO.getSize().get(2) : null
        );
    }
}
