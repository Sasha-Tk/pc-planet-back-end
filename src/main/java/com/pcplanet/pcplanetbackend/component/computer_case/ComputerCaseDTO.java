package com.pcplanet.pcplanetbackend.component.computer_case;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ComputerCaseDTO extends ComponentDTO {
    private List<String> motherboardFormFactor;
    private Float maxCpuFanHeight;
    private Float maxGpuWidth;
    private Short caseFanCount;
}
