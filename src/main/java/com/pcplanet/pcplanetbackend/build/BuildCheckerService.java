package com.pcplanet.pcplanetbackend.build;

import com.pcplanet.pcplanetbackend.component.case_fan.CaseFan;
import com.pcplanet.pcplanetbackend.component.case_fan.CaseFanService;
import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCase;
import com.pcplanet.pcplanetbackend.component.computer_case.ComputerCaseService;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilter;
import com.pcplanet.pcplanetbackend.component.cpu.CPU;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilter;
import com.pcplanet.pcplanetbackend.component.cpu.CPUService;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFan;
import com.pcplanet.pcplanetbackend.component.cpu_fan.CPUFanService;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilter;
import com.pcplanet.pcplanetbackend.component.gpu.GPU;
import com.pcplanet.pcplanetbackend.component.gpu.GPUService;
import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilter;
import com.pcplanet.pcplanetbackend.component.hdd.HDD;
import com.pcplanet.pcplanetbackend.component.hdd.HDDService;
import com.pcplanet.pcplanetbackend.component.motherboard.Motherboard;
import com.pcplanet.pcplanetbackend.component.motherboard.MotherboardService;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilter;
import com.pcplanet.pcplanetbackend.component.psu.PSU;
import com.pcplanet.pcplanetbackend.component.psu.PSUService;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilter;
import com.pcplanet.pcplanetbackend.component.ram.RAM;
import com.pcplanet.pcplanetbackend.component.ram.RAMService;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilter;
import com.pcplanet.pcplanetbackend.component.ssd.SSD;
import com.pcplanet.pcplanetbackend.component.ssd.SSDService;
import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildCheckerService {
    private final BuildMapper buildMapper;
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


    public BuildCheckResponse checkCompatibility(BuildDTO buildDTO) {
        BuildCheckResponse result = new BuildCheckResponse();
        Build build = buildMapper.mapToEntity(buildDTO);
        Motherboard motherboardFromBuild = build.getMotherboard();
        CPU cpuFromBuild = build.getCpu();
        RAM ramFromBuild = build.getRam();
        GPU gpuFromBuild = build.getGpu();
        PSU psuFromBuild = build.getPsu();
        ComputerCase computerCaseFromBuild = build.getComputerCase();
        SSD ssdFromBuild = build.getSsd();
        HDD hddFromBuild = build.getHdd();
        CPUFan cpuFanFromBuild = build.getCpuFan();
        CaseFan caseFanFromBuild = build.getCaseFan();

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

        if (gpuFromBuild != null && psuFromBuild != null) {
            if (gpuFromBuild.getRecommendedPsuPower() > psuFromBuild.getPower()) {
                result.getGpu().add("This GPU is too powerful for selected PSU");
                result.getPsu().add("This PSU has too low power for selected GPU");
            }
            if (!gpuFromBuild.getAdditionalPower().equals("-") && psuFromBuild.getNumberOfAdditionalPowerConnectorsForGpu() == 0) {
                result.getGpu().add("This GPU can not be powered by selected PSU");
                result.getPsu().add("This PSU can not power selected GPU");
            }
        }

        if (gpuFromBuild != null && computerCaseFromBuild != null) {
            if (gpuFromBuild.getWidth() > computerCaseFromBuild.getMaxGpuWidth()) {
                result.getGpu().add("This GPU is too large for selected case");
                result.getComputerCase().add("This case is too small for selected GPU");
            }
        }

        if (computerCaseFromBuild != null && motherboardFromBuild != null) {
            if (!computerCaseFromBuild.getMotherboardFormFactor().contains(motherboardFromBuild.getFormFactor())) {
                result.getComputerCase().add("This case does not support the selected motherboard form factor");
                result.getMotherboard().add("This motherboard is not compatible with the selected case form factor");
            }
        }

        if (ssdFromBuild != null && hddFromBuild != null && motherboardFromBuild != null && ssdFromBuild.getFormFactor().contains("2.5")) {
            if (motherboardFromBuild.getM2ConnectorsAmount() < 2) {
                result.getMotherboard().add("This motherboard does not have enough SATA connectors");
                result.getSsd().add("This SSD cannot be connected to the motherboard");
                result.getHdd().add("This HDD cannot be connected to the motherboard");
            }
        }

        if (ssdFromBuild != null && motherboardFromBuild != null && ssdFromBuild.getFormFactor().contains("M.2")) {
            if (motherboardFromBuild.getM2ConnectorsAmount() == 0) {
                result.getSsd().add("This SSD is not compatible with the selected motherboard");
                result.getMotherboard().add("This motherboard does not support the M.2 ssd form factor");
            }
        }

        if (cpuFanFromBuild != null && motherboardFromBuild != null) {
            if (!cpuFanFromBuild.getSocket().contains(motherboardFromBuild.getSocket())) {
                result.getCpuFan().add("This CPU fan does not support the selected motherboard socket");
                result.getMotherboard().add("This motherboard does not support the selected CPU fan socket");
            }
        }

        if (cpuFanFromBuild != null && cpuFromBuild != null) {
            if (!cpuFanFromBuild.getSocket().contains(cpuFromBuild.getSocket())) {
                result.getCpuFan().add("This CPU fan does not support the selected CPU socket");
                result.getCpu().add("This CPU does not support the selected CPU fan socket");
            }
            if (cpuFromBuild.getTdpPower() > cpuFanFromBuild.getMaxTdp()) {
                result.getCpuFan().add("This CPU fan has too low power for selected CPU");
                result.getCpu().add("This CPU is too powerful for selected CPU fan");
            }
        }

        if (cpuFanFromBuild != null && computerCaseFromBuild != null) {
            if (cpuFanFromBuild.getHeight() > computerCaseFromBuild.getMaxCpuFanHeight()) {
                result.getCpuFan().add("This CPU fan is too large for selected computer case");
                result.getComputerCase().add("This computer case is too small for selected CPU fan");
            }
        }

        return result;
    }

    public ResponseFiltersInfo getFiltersForBuild(BuildDTO buildDTO) {
        Build build = buildMapper.mapToEntity(buildDTO);
        ResponseFiltersInfo responseFiltersInfo = new ResponseFiltersInfo();
        responseFiltersInfo.addNewFilter("motherboard", getFilterForMotherboard(build).getFilters());
        responseFiltersInfo.addNewFilter("cpu", getFilterForCPU(build).getFilters());
        responseFiltersInfo.addNewFilter("ram", getFilterForRAM(build).getFilters());
        responseFiltersInfo.addNewFilter("gpu", getFiltersForGPU(build).getFilters());
        responseFiltersInfo.addNewFilter("psu", getFiltersForPSU(build).getFilters());
        responseFiltersInfo.addNewFilter("computerCase", getFiltersForComputerCase(build).getFilters());
        responseFiltersInfo.addNewFilter("ssd", getFiltersForSSD(build).getFilters());
        responseFiltersInfo.addNewFilter("cpuFan", getFiltersForCPUFan(build).getFilters());
        return responseFiltersInfo;
    }

    public ComponentFilterDTOResponse getFilterForMotherboard(Build build) {
        Motherboard motherboardFromBuild = build.getMotherboard();
        CPU cpuFromBuild = build.getCpu();
        RAM ramFromBuild = build.getRam();
        SSD ssdFromBuild = build.getSsd();
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
            if (ssdFromBuild != null && ssdFromBuild.getFormFactor().contains("M.2")) {
                var filter = allMotherboardFilters.getFilterByName("m2ConnectorsAmount");
                filter.setAvailableFilters(List.of(1, filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);

            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFilterForCPU(Build build) {
        CPU cpuFromBuild = build.getCpu();
        Motherboard motherboardFromBuild = build.getMotherboard();
        RAM ramFromBuild = build.getRam();
        CPUFan cpuFanFromBuild = build.getCpuFan();
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
            if (cpuFanFromBuild != null) {
                var filter = allCpuFilters.getFilterByName("socket");
                filter.setAvailableFilters(cpuFanFromBuild.getSocket()
                        .stream()
                        .map(Socket::getSocketName)
                        .toList());
                filtersToApply.addFilter(filter);
                filter = allCpuFilters.getFilterByName("tdpPower");
                filter.setAvailableFilters(List.of(filter.getAvailableFilters().get(0), cpuFanFromBuild.getMaxTdp()));
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
                if (psuFromBuild.getNumberOfAdditionalPowerConnectorsForGpu() == 0) {
                    filter = allGpuFilters.getFilterByName("additionalPower");
                    filter.setAvailableFilters(List.of("-"));
                    filtersToApply.addFilter(filter);
                }
            }
            if (computerCaseFromBuild != null) {
                var filter = allGpuFilters.getFilterByName("width");
                filter.setAvailableFilters(List.of(filter.getAvailableFilters().get(0), Math.min(computerCaseFromBuild.getMaxGpuWidth(), (Float) filter.getAvailableFilters().get(1))));
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
                Assert.notEmpty(filter.getAvailableFilters(), "Filters can not be empty!");
                filter.setAvailableFilters(List.of(gpuFromBuild.getRecommendedPsuPower(), filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);
                if (!gpuFromBuild.getAdditionalPower().equals("-")) {
                    filter = allPsuFilters.getFilterByName("numberOfAdditionalPowerConnectorsForGpu");
                    filter.setAvailableFilters(List.of(1, filter.getAvailableFilters().get(1)));
                }
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFiltersForComputerCase(Build build) {
        ComputerCase computerCaseFromBuild = build.getComputerCase();
        Motherboard motherboardFromBuild = build.getMotherboard();
        GPU gpuFromBuild = build.getGpu();
        CPUFan cpuFanFromBuild = build.getCpuFan();
        ComponentFilterDTOResponse allComputerCaseFilters = computerCaseService.getAllComponentFilters(ComputerCase.class, ComputerCaseFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (computerCaseFromBuild == null) {
            if (motherboardFromBuild != null) {
                var filter = allComputerCaseFilters.getFilterByName("motherboardFormFactor");
                filter.setAvailableFilters(List.of(motherboardFromBuild.getFormFactor().getFormFactorName()));
                filtersToApply.addFilter(filter);
            }
            if (gpuFromBuild != null) {
                var filter = allComputerCaseFilters.getFilterByName("maxGpuWidth");
                filter.setAvailableFilters(List.of(gpuFromBuild.getWidth(), filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);
            }
            if (cpuFanFromBuild != null) {
                var filter = allComputerCaseFilters.getFilterByName("maxCpuFanHeight");
                filter.setAvailableFilters(List.of(cpuFanFromBuild.getHeight(), filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFiltersForSSD(Build build) {
        SSD ssdFromBuild = build.getSsd();
        Motherboard motherboardFromBuild = build.getMotherboard();
        ComponentFilterDTOResponse allSSDFilters = ssdService.getAllComponentFilters(SSD.class, SSDFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (ssdFromBuild == null) {
            if (motherboardFromBuild != null) {
                if (motherboardFromBuild.getSataConnectorsAmount() > 0 && motherboardFromBuild.getM2ConnectorsAmount() == 0) {
                    var filter = allSSDFilters.getFilterByName("sddConnectionInterface");
                    filter.setAvailableFilters(filter.getAvailableFilters().stream().filter(f -> String.valueOf(f).contains("SATA")).toList());
                    filtersToApply.addFilter(filter);
                    filter = allSSDFilters.getFilterByName("formFactor");
                    filter.setAvailableFilters(filter.getAvailableFilters().stream().filter(f -> String.valueOf(f).contains("2.5")).toList());
                    filtersToApply.addFilter(filter);
                } else {
                    filtersToApply.addFilter(allSSDFilters.getFilterByName("sddConnectionInterface"));
                    filtersToApply.addFilter(allSSDFilters.getFilterByName("formFactor"));
                }
            }
        }
        return filtersToApply;
    }

    public ComponentFilterDTOResponse getFiltersForCPUFan(Build build) {
        CPUFan cpuFanFromBuild = build.getCpuFan();
        Motherboard motherboardFromBuild = build.getMotherboard();
        CPU cpuFromBuild = build.getCpu();
        ComputerCase computerCaseFromBuild = build.getComputerCase();
        ComponentFilterDTOResponse allCPUFanFilters = cpuFanService.getAllComponentFilters(CPUFan.class, CPUFanFilter.class);
        ComponentFilterDTOResponse filtersToApply = new ComponentFilterDTOResponse();
        if (cpuFanFromBuild == null) {
            if (motherboardFromBuild != null) {
                var filter = allCPUFanFilters.getFilterByName("socket");
                filter.setAvailableFilters(List.of(motherboardFromBuild.getSocket().getSocketName()));
                filtersToApply.addFilter(filter);
            }
            if (cpuFromBuild != null) {
                var filter = allCPUFanFilters.getFilterByName("socket");
                filter.setAvailableFilters(List.of(cpuFromBuild.getSocket().getSocketName()));
                filtersToApply.addFilter(filter);
                filter = allCPUFanFilters.getFilterByName("maxTdp");
                filter.setAvailableFilters(List.of(cpuFromBuild.getTdpPower(), filter.getAvailableFilters().get(1)));
                filtersToApply.addFilter(filter);
            }
            if (computerCaseFromBuild != null) {
                var filter = allCPUFanFilters.getFilterByName("height");
                filter.setAvailableFilters(List.of(filter.getAvailableFilters().get(0), Math.min(computerCaseFromBuild.getMaxCpuFanHeight(), (Float) filter.getAvailableFilters().get(1))));
                filtersToApply.addFilter(filter);
            }
        }
        return filtersToApply;
    }
}
