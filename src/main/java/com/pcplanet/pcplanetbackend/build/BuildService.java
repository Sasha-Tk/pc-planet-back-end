package com.pcplanet.pcplanetbackend.build;

import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCase;
import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCaseService;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilter;
import com.pcplanet.pcplanetbackend.component.cpu.CPU;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilter;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUService;
import com.pcplanet.pcplanetbackend.component.gpu.GPU;
import com.pcplanet.pcplanetbackend.component.gpu.GPUService;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilter;
import com.pcplanet.pcplanetbackend.component.motherboard.Motherboard;
import com.pcplanet.pcplanetbackend.component.motherboard.MotherboardService;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilter;
import com.pcplanet.pcplanetbackend.component.psu.PSU;
import com.pcplanet.pcplanetbackend.component.psu.PSUService;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilter;
import com.pcplanet.pcplanetbackend.component.ram.RAM;
import com.pcplanet.pcplanetbackend.component.ram.RAMService;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildService {
    private final BuildMapper buildMapper;
    private final GPUService gpuService;
    private final PSUService psuService;
    private final CPUService cpuService;
    private final MotherboardService motherboardService;
    private final RAMService ramService;
    private final ComputerCaseService computerCaseService;


    public BuildCheckResponse checkCompatibility(BuildDTO buildDTO) {
        BuildCheckResponse result = new BuildCheckResponse();
        Build build = buildMapper.mapToEntity(buildDTO);
        GPU gpuFromBuild = build.getGpu();
        PSU psuFromBuild = build.getPsu();
        Motherboard motherboardFromBuild = build.getMotherboard();
        CPU cpuFromBuild = build.getCpu();
        RAM ramFromBuild = build.getRam();
        ComputerCase computerCaseFromBuild = build.getComputerCase();

        if (gpuFromBuild != null && psuFromBuild != null) {
            if (gpuFromBuild.getRecommendedPsuPower() > psuFromBuild.getPower()) {
                result.getGpu().add("This GPU is too powerful for selected PSU");
                result.getPsu().add("This PSU has too low power for selected GPU");
            }
        }

        if (gpuFromBuild != null && computerCaseFromBuild != null) {
            if (gpuFromBuild.getWidth() > computerCaseFromBuild.getMaxGpuWidth()) {
                result.getGpu().add("This GPU is too big for selected case");
                result.getComputerCase().add("This case is too small for selected GPU");
            }
        }

        if (computerCaseFromBuild != null && motherboardFromBuild != null) {
            if (!computerCaseFromBuild.getMotherboardFormFactor().contains(motherboardFromBuild.getFormFactor())) {
                result.getComputerCase().add("This case does not support the selected motherboard form factor");
                result.getMotherboard().add("This motherboard is not compatible with the selected case form factor");
            }
        }

        if (motherboardFromBuild != null && cpuFromBuild != null) {
            if (!motherboardFromBuild.getSocket().getSocketName().equals(cpuFromBuild.getSocket().getSocketName())) {
                result.getMotherboard().add("This motherboard does not support the selected CPU socket");
                result.getCpu().add("This CPU socket is not compatible with the selected motherboard");
            }
        }

        if (motherboardFromBuild != null && ramFromBuild != null) {
            if (!motherboardFromBuild.getMemoryType().equals(ramFromBuild.getMemoryType())) {
                result.getMotherboard().add("This motherboard does not support the selected memory type of RAM");
                result.getRam().add("This RAM memory type is not compatible with the selected motherboard");
            }
        }

        if (ramFromBuild != null && cpuFromBuild != null) {
            if (!ramFromBuild.getMemoryType().equals(cpuFromBuild.getMemoryType())) {
                result.getRam().add("This RAM memory type is not compatible with the selected CPU memory type");
                result.getCpu().add("This CPU memory type is not compatible with the selected RAM memory type");
            }
        }

        return result;
    }

    public ResponseFiltersInfo getFiltersForBuild(BuildDTO buildDTO) {
        Build build = buildMapper.mapToEntity(buildDTO);
        ResponseFiltersInfo responseFiltersInfo = new ResponseFiltersInfo();
        responseFiltersInfo.addNewFilter("gpu", getFiltersForGPU(build).getFilters());
        responseFiltersInfo.addNewFilter("psu", getFiltersForPSU(build).getFilters());
        responseFiltersInfo.addNewFilter("cpu", getFilterForCPU(build).getFilters());
        responseFiltersInfo.addNewFilter("motherboard", getFilterForMotherboard(build).getFilters());
        responseFiltersInfo.addNewFilter("ram", getFilterForRAM(build).getFilters());
        responseFiltersInfo.addNewFilter("computerCase", getFiltersForComputerCase(build).getFilters());
        System.out.println(responseFiltersInfo);
        return responseFiltersInfo;
    }

    public ComponentFilterDTOResponse getFiltersForComputerCase(Build build) {
        ComputerCase computerCaseFromBuild = build.getComputerCase();
        GPU gpuFromBuild = build.getGpu();
        Motherboard motherboardFromBuild = build.getMotherboard();
        ComponentFilterDTOResponse allComputerCaseFilters = computerCaseService.getAllComponentFilters(ComputerCase.class, ComputerCaseFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (computerCaseFromBuild == null) {
            if (gpuFromBuild != null) {
                var filter = allComputerCaseFilters.getFilterByName("maxGpuWidth");
                filter.setAvailableFilters(List.of(gpuFromBuild.getWidth(), filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);
            }
            if (motherboardFromBuild != null) {
                var filter = allComputerCaseFilters.getFilterByName("motherboardFormFactor");
                filter.setAvailableFilters(List.of(motherboardFromBuild.getFormFactor().getFormFactorName()));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFilterForRAM(Build build) {
        RAM ramFromBuild = build.getRam();
        Motherboard motherboardFromBuild = build.getMotherboard();
        CPU cpuFromBuild = build.getCpu();
        ComponentFilterDTOResponse allRamFilters = ramService.getAllComponentFilters(RAM.class, RAMFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (ramFromBuild == null) {
            if (cpuFromBuild != null) {
                var filter = allRamFilters.getFilterByName("memoryType");
                filter.setAvailableFilters(List.of(cpuFromBuild.getMemoryType()));
                filtersToApply.addFilter(filter);
            }
            if (motherboardFromBuild != null) {
                var filter = allRamFilters.getFilterByName("memoryType");
                filter.setAvailableFilters(List.of(motherboardFromBuild.getMemoryType()));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFilterForMotherboard(Build build) {
        Motherboard motherboardFromBuild = build.getMotherboard();
        CPU cpuFromBuild = build.getCpu();
        RAM ramFromBuild = build.getRam();
        ComponentFilterDTOResponse allMotherboardFilters = motherboardService.getAllComponentFilters(Motherboard.class, MotherboardFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (motherboardFromBuild == null) {
            if (cpuFromBuild != null) {
                var filter = allMotherboardFilters.getFilterByName("socket");
                filter.setAvailableFilters(List.of(cpuFromBuild.getSocket().getSocketName()));
                filtersToApply.addFilter(filter);
            }
            if (ramFromBuild != null) {
                var filter = allMotherboardFilters.getFilterByName("memoryType");
                filter.setAvailableFilters(List.of(ramFromBuild.getMemoryType()));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFilterForCPU(Build build) {
        CPU cpuFromBuild = build.getCpu();
        Motherboard motherboardFromBuild = build.getMotherboard();
        RAM ramFromBuild = build.getRam();
        ComponentFilterDTOResponse allCpuFilters = cpuService.getAllComponentFilters(CPU.class, CPUFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (cpuFromBuild == null) {
            if (motherboardFromBuild != null) {
                var filter = allCpuFilters.getFilterByName("socket");
                filter.setAvailableFilters(List.of(motherboardFromBuild.getSocket().getSocketName()));
                filtersToApply.addFilter(filter);
            }
            if (ramFromBuild != null) {
                var filter = allCpuFilters.getFilterByName("memoryType");
                filter.setAvailableFilters(List.of(ramFromBuild.getMemoryType()));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFiltersForPSU(Build build) {
        PSU psuFromBuild = build.getPsu();
        GPU gpuFromBuild = build.getGpu();
        ComponentFilterDTOResponse allPsuFilters = psuService.getAllComponentFilters(PSU.class, PSUFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (psuFromBuild == null) {
            if (gpuFromBuild != null) {
                var filter = allPsuFilters.getFilterByName("power");
                Assert.notEmpty(filter.getAvailableFilters(),"Filters can not be empty!");
                filter.setAvailableFilters(List.of(gpuFromBuild.getRecommendedPsuPower(), filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFiltersForGPU(Build build) {
        GPU gpuFromBuild = build.getGpu();
        PSU psuFromBuild = build.getPsu();
        ComputerCase computerCaseFromBuild = build.getComputerCase();
        ComponentFilterDTOResponse allGpuFilters = gpuService.getAllComponentFilters(GPU.class, GPUFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (gpuFromBuild == null) {
            if (psuFromBuild != null) {
                var filter = allGpuFilters.getFilterByName("recommendedPsuPower");
                filter.setAvailableFilters(List.of(filter.getAvailableFilters().get(0), psuFromBuild.getPower()));
                filtersToApply.addFilter(filter);
            }
            if (computerCaseFromBuild != null) {
                var filter = allGpuFilters.getFilterByName("width");
                filter.setAvailableFilters(List.of(filter.getAvailableFilters().get(0), Math.min(computerCaseFromBuild.getMaxGpuWidth(), (Float) filter.getAvailableFilters().get(1))));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }
}
