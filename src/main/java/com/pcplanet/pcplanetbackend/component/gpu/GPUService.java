package com.pcplanet.pcplanetbackend.component.gpu;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilter;
import com.pcplanet.pcplanetbackend.component.gpu.filter.GPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GPUService extends ComponentService<GPU, GPUDTO, GPUFilter, GPUFilterDTO> {
    @Autowired
    public GPUService(Mapper<GPUDTO, GPU> componentMapper,
                      Mapper<GPUFilterDTO, GPUFilter> componentFilterMapper,
                      ComponentRepository<GPU> componentRepository,
                      PartialUpdate<GPU> partialUpdate,
                      FilterDAO<GPU, GPUFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
