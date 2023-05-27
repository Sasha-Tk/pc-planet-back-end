package com.pcplanet.pcplanetbackend.component.psu;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilter;
import com.pcplanet.pcplanetbackend.component.psu.filter.PSUFilterDTO;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PSUService extends ComponentService<PSU,PSUDTO, PSUFilter, PSUFilterDTO> {
    @Autowired
    public PSUService(Mapper<PSUDTO, PSU> componentMapper,
                      Mapper<PSUFilterDTO, PSUFilter> componentFilterMapper,
                      ComponentRepository<PSU> componentRepository,
                      PartialUpdate<PSU> partialUpdate,
                      FilterDAO<PSU, PSUFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
