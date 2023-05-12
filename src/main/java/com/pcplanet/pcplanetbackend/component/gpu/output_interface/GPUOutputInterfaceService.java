package com.pcplanet.pcplanetbackend.component.gpu.output_interface;

import com.pcplanet.pcplanetbackend.exception.component_exception.gpu_exception.NoSuchOutputInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GPUOutputInterfaceService {
    private final GPUOutputInterfaceRepository gpuOutputInterfaceRepository;

    public boolean outputInterfaceAlreadyExist(String interfaceName) {
        return gpuOutputInterfaceRepository.existsByInterfaceName(interfaceName);
    }

    public GPUOutputInterface createInterface(String interfaceName) {
        return gpuOutputInterfaceRepository.save(new GPUOutputInterface(interfaceName));
    }

    public GPUOutputInterface findInterfaceByInterfaceName(String interfaceName) {
        return gpuOutputInterfaceRepository
                .findByInterfaceName(interfaceName)
                .orElseThrow(() -> new NoSuchOutputInterface("No output interface with this name"));
    }

    public List<GPUOutputInterface> createInterfacesOrGetExisting(List<String> interfacesNames) {
        List<GPUOutputInterface> createdInterfaces = new ArrayList<>();
        interfacesNames.forEach(gpuOutputInterfaceName ->
                createdInterfaces.add(outputInterfaceAlreadyExist(gpuOutputInterfaceName) ?
                        findInterfaceByInterfaceName(gpuOutputInterfaceName) :
                        createInterface(gpuOutputInterfaceName)));
        return createdInterfaces;
    }
}
