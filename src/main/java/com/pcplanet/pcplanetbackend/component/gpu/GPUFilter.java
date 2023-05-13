package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterface;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPUFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<String> chip = new ArrayList<>();
    private List<Short> memoryAmount = new ArrayList<>();
    private List<String> memoryType = new ArrayList<>();
    private List<Integer> memoryInterfaceWidth = new ArrayList<>();
    private List<String> connectionInterface = new ArrayList<>();
    private List<String> additionalPower = new ArrayList<>();
    private List<GPUOutputInterface> outputInterfaces = new ArrayList<>();
}
