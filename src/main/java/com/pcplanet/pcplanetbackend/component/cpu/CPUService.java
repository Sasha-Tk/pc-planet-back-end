package com.pcplanet.pcplanetbackend.component.cpu;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.cpu.CPU;
import com.pcplanet.pcplanetbackend.component.cpu.CPUDTO;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilter;
import com.pcplanet.pcplanetbackend.component.cpu.filter.CPUFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPUService extends ComponentService<CPU, CPUDTO, CPUFilter, CPUFilterDTO> {
    @Autowired
    public CPUService(Mapper<CPUDTO, CPU> componentMapper,
                      Mapper<CPUFilterDTO, CPUFilter> componentFilterMapper,
                      ComponentRepository<CPU> componentRepository,
                      PartialUpdate<CPU> partialUpdate,
                      FilterDAO<CPU, CPUFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
