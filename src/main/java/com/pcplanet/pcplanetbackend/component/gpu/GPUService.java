package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.exception.component_exception.gpu_exception.NoSuchGPUException;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GPUService {
    private final Mapper<GPUDTO, GPU> gpuMapper;
    private final Mapper<GPUFilterDTO, GPUFilter> gpuFilterMapper;
    private final GPURepository gpuRepository;
    private final PartialUpdate<GPU, GPU> partialUpdate;
    private final FilterDAO<GPU, GPUFilter> filterDAO;


    public GPU findGPUById(Long id) {
        return gpuRepository.findById(id).orElseThrow(() -> new NoSuchGPUException("No such gpu with this id"));
    }

    public GPU createGPU(GPUDTO gpuDTO) {
        return gpuRepository.save(gpuMapper.mapToEntity(gpuDTO));
    }

    public void deleteGPU(Long id) {
        gpuRepository.deleteById(findGPUById(id).getId());
    }

    public GPU updateGPU(Long id, GPUDTO gpuDTO) {
        GPU gpuForUpdate = findGPUById(id);
        GPU updatedGPU = gpuMapper.mapToEntity(gpuDTO);
        return gpuRepository.save(partialUpdate.update(gpuForUpdate, updatedGPU));
    }

    public List<GPU> findGPUByFilterParameters(GPUFilterDTO gpuFilterDTO) {
        return filterDAO.findComponentsByFilter(GPU.class, gpuFilterMapper.mapToEntity(gpuFilterDTO));
    }

    public List<GPU> getAllGPU() {
        return gpuRepository.findAll();
    }

    public GPUFilterDTO getAllGPUFilters() {
        return gpuFilterMapper.mapToDTO(filterDAO.getFilter(GPU.class, GPUFilter.class));
    }


}
