package com.pcplanet.pcplanetbackend.component.case_fan;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CaseFanDTO extends ComponentDTO {
    private Short fanSize;
    private List<String> fanPowerConnector;
    private Short rpm;
    private Float maxNoiseLevel;
    private Short caseFanCount;
}
