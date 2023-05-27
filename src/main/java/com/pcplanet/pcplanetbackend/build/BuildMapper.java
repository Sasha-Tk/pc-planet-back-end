package com.pcplanet.pcplanetbackend.build;

import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCaseService;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUService;
import com.pcplanet.pcplanetbackend.component.gpu.GPUService;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.motherboard.MotherboardService;
import com.pcplanet.pcplanetbackend.component.psu.PSUService;
import com.pcplanet.pcplanetbackend.component.ram.RAMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildMapper implements Mapper<BuildDTO, Build> {
    private final GPUService gpuService;
    private final PSUService psuService;
    private final MotherboardService motherboardService;
    private final CPUService cpuService;
    private final RAMService ramService;
    private final ComputerCaseService computerCaseService;

    @Override
    public Build mapToEntity(BuildDTO buildDTO) {
        return new Build(
                buildDTO.getGpu() == null ? null : gpuService.findComponentBySku(buildDTO.getGpu()),
                buildDTO.getPsu() == null ? null : psuService.findComponentBySku(buildDTO.getPsu()),
                buildDTO.getMotherboard() == null ? null : motherboardService.findComponentBySku(buildDTO.getMotherboard()),
                buildDTO.getCpu() == null ? null : cpuService.findComponentBySku(buildDTO.getCpu()),
                buildDTO.getRam() == null ? null : ramService.findComponentBySku(buildDTO.getRam()),
                buildDTO.getComputerCase() == null ? null : computerCaseService.findComponentBySku(buildDTO.getComputerCase())
        );
    }
}
