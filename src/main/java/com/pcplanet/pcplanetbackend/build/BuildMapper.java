package com.pcplanet.pcplanetbackend.build;

import com.pcplanet.pcplanetbackend.component.case_fan.CaseFanService;
import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCaseService;
import com.pcplanet.pcplanetbackend.component.cpu.CPUService;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFanService;
import com.pcplanet.pcplanetbackend.component.gpu.GPUService;
import com.pcplanet.pcplanetbackend.component.hdd.HDDService;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.motherboard.MotherboardService;
import com.pcplanet.pcplanetbackend.component.psu.PSUService;
import com.pcplanet.pcplanetbackend.component.ram.RAMService;
import com.pcplanet.pcplanetbackend.component.ssd.SSDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildMapper implements Mapper<BuildDTO, Build> {
    private final MotherboardService motherboardService;
    private final CPUService cpuService;
    private final RAMService ramService;
    private final GPUService gpuService;
    private final PSUService psuService;
    private final ComputerCaseService computerCaseService;
    private final SSDService ssdService;
    private final HDDService hddService;
    private final CPUFanService cpuFanService;
    private final CaseFanService caseFanService;

    @Override
    public Build mapToEntity(BuildDTO buildDTO) {
        return new Build(
                buildDTO.getMotherboard() == null ? null : motherboardService.findComponentBySku(buildDTO.getMotherboard()),
                buildDTO.getCpu() == null ? null : cpuService.findComponentBySku(buildDTO.getCpu()),
                buildDTO.getRam() == null ? null : ramService.findComponentBySku(buildDTO.getRam()),
                buildDTO.getGpu() == null ? null : gpuService.findComponentBySku(buildDTO.getGpu()),
                buildDTO.getPsu() == null ? null : psuService.findComponentBySku(buildDTO.getPsu()),
                buildDTO.getComputerCase() == null ? null : computerCaseService.findComponentBySku(buildDTO.getComputerCase()),
                buildDTO.getSsd() == null ? null : ssdService.findComponentBySku(buildDTO.getSsd()),
                buildDTO.getHdd() == null ? null : hddService.findComponentBySku(buildDTO.getHdd()),
                buildDTO.getCpuFan() == null ? null : cpuFanService.findComponentBySku(buildDTO.getCpuFan()),
                buildDTO.getCaseFan() == null ? null : caseFanService.findComponentBySku(buildDTO.getCaseFan())
        );
    }
}
