package com.pcplanet.pcplanetbackend.component;

import com.pcplanet.pcplanetbackend.component.gpu.filter.ComponentFilterDTOResponse;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistory;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.Store;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ComponentService<
        C extends Component, CDTO extends ComponentDTO,
        F extends ComponentFilter, FDTO extends ComponentFilter> {

    private Mapper<CDTO, C> componentMapper;
    private Mapper<FDTO, F> componentFilterMapper;
    private ComponentRepository<C> componentRepository;
    private PartialUpdate<C> partialUpdate;
    private FilterDAO<C, F> filterDAO;
    private StoreService storeService;
    private PriceHistoryService priceHistoryService;


    public C createComponent(CDTO componentDTO, String storeName, Integer price) {
        Store store = storeService.createNewStoreOrGetExisting(storeName);
        PriceHistory priceHistory = priceHistoryService.createPriceHistory(new PriceHistory(store, price));
        Optional<C> componentBySku = componentRepository.findBySku(componentDTO.getSku());
        C component = componentBySku.orElseGet(() -> componentMapper.mapToEntity(componentDTO));
        component.addPriceHistory(priceHistory);
        return componentRepository.save(component);
    }

    public C findComponentById(Long id) {
        return componentRepository.findById(id).orElseThrow();
    }

    public C findComponentBySku(String sku) {
        return componentRepository.findBySku(sku).orElseThrow();
    }

    public ComponentListResponse<C> getAllComponents(
            FDTO componentFilterDTO,
            Integer page,
            Integer pageSize,
            Optional<String> sortField,
            String sortingOrder,
            Class<C> componentClass
    ) {
        return filterDAO.findComponentsByFilter(
                componentClass,
                componentFilterMapper.mapToEntity(componentFilterDTO),
                PageRequest.of(
                        page,
                        pageSize,
                        sortField.map(sort -> Sort.by(Sort.Direction.fromString(sortingOrder), sort)).orElseGet(Sort::unsorted))
        );
    }

    public List<C> getAllComponents() {
        return componentRepository.findAll();
    }

    public C updateComponent(Long id, CDTO componentDTO, Mapper<CDTO, C> componentMapper) {
        C componentForUpdate = findComponentById(id);
        C updatedComponent = partialUpdate.update(componentForUpdate, componentMapper.mapToEntity(componentDTO));
        return componentRepository.save(updatedComponent);
    }

    public void deleteComponent(Long id) {
        componentRepository.delete(findComponentById(id));
    }

    public ComponentFilterDTOResponse getAllComponentFilters(Class<C> componentClass, Class<F> componentFilterClass) {
        return new ComponentFilterDTOResponse(componentFilterMapper.mapToDTO(filterDAO.getFilter(componentClass, componentFilterClass)));
    }
}
