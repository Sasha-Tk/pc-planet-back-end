package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.*;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilter;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistory;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.exception.component_exception.gpu_exception.NoSuchGPUException;
import com.pcplanet.pcplanetbackend.store.Store;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GPUService {
    private final Mapper<GPUDTO, GPU> gpuMapper;
    private final Mapper<GPUFilterDTO, GPUFilter> gpuFilterMapper;
    private final GPURepository gpuRepository;
    private final PartialUpdate<GPU, GPU> partialUpdate;
    private final FilterDAO<GPU, GPUFilter> filterDAO;
    private final ComponentService componentService;
    private final StoreService storeService;
    private final PriceHistoryService priceHistoryService;

    public GPU findGPUById(Long id) {
        return gpuRepository.findById(id).orElseThrow(() -> new NoSuchGPUException("No such gpu with this id"));
    }

    public GPU createGPU(GPUDTO gpuDTO, String storeName, Integer price) {
        Store store = storeService.createNewStoreOrGetExisting(storeName);
        PriceHistory priceHistory = priceHistoryService.createPriceHistory(new PriceHistory(store, price));

        Optional<Component> componentBySku = componentService.findBySku(gpuDTO.getSku());
        if (componentBySku.isEmpty()) {
            GPU newGPU = gpuMapper.mapToEntity(gpuDTO);
            newGPU.getPriceHistoryList().add(priceHistory);
            return gpuRepository.save(newGPU);
        } else {
            GPU existingGPU = findGPUById(componentBySku.get().getId());
            existingGPU.getPriceHistoryList().add(priceHistory);
            return gpuRepository.save(existingGPU);
        }
    }

    public void deleteGPU(Long id) {
        gpuRepository.deleteById(findGPUById(id).getId());
    }

    public GPU updateGPU(Long id, GPUDTO gpuDTO) {
        GPU gpuForUpdate = findGPUById(id);
        GPU updatedGPU = gpuMapper.mapToEntity(gpuDTO);
        return gpuRepository.save(partialUpdate.update(gpuForUpdate, updatedGPU));
    }

    public ComponentListResponse<GPU> getAllGPUByParameters(
            GPUFilterDTO gpuFilterDTO,
            Integer page,
            Integer pageSize,
            Optional<String> sortBy,
            String sortingOrder) {

        return filterDAO.findComponentsByFilter(
                GPU.class,
                gpuFilterMapper.mapToEntity(gpuFilterDTO == null ? new GPUFilterDTO() : gpuFilterDTO),
                PageRequest.of(
                        page,
                        pageSize,
                        sortBy.map(sort -> Sort.by(Sort.Direction.fromString(sortingOrder), sort))
                                .orElseGet(Sort::unsorted)
                )
        );
    }

    public List<GPU> getAllGPU() {
        return gpuRepository.findAll();
    }

    public GPUFilterDTOResponse getAllGPUFilters() {
        return new GPUFilterDTOResponse(gpuFilterMapper.mapToDTO(filterDAO.getFilter(GPU.class, GPUFilter.class)));
    }
}
