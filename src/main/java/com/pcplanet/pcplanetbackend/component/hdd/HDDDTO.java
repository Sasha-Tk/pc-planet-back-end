package com.pcplanet.pcplanetbackend.component.hdd;

import com.pcplanet.pcplanetbackend.component.ComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HDDDTO extends ComponentDTO {
    private String driveCapacity;
    private String hddConnectionInterface;
    private String formFactor;
    private Short dataTransferSpeed;
    private Short rotationSpeed;
}
