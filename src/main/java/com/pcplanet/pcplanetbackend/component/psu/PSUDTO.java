package com.pcplanet.pcplanetbackend.component.psu;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PSUDTO extends ComponentDTO {
    private Short power;
    private String cpuPowerConnector;
    private Short numberOfAdditionalPowerConnectorsForGpu;
    private Short cooling;
    private String typeOfAdditionalPowerConnectorsForGpu;
    private Short numberOfSataConnectors;
}
