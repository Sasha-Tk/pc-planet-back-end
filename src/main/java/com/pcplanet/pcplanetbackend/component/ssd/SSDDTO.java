package com.pcplanet.pcplanetbackend.component.ssd;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SSDDTO extends ComponentDTO {
    private String driveCapacity;
    private String sddConnectionInterface;
    private String formFactor;
    private Short readSpeed;
    private Short writeSpeed;
}
