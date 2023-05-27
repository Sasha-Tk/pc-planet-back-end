package com.pcplanet.pcplanetbackend.component.ram;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RAMDTO extends ComponentDTO {
    private String memoryType;
    private Short memoryAmount;
    private Short memoryFrequency;
    private Short barsNumber;
    private String formFactor;
}
