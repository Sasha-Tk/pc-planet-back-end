package com.pcplanet.pcplanetbackend.component.ssd;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilter;
import com.pcplanet.pcplanetbackend.component.ssd.filter.SSDFilterDTO;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSDService extends ComponentService<SSD, SSDDTO, SSDFilter, SSDFilterDTO> {
    @Autowired
    public SSDService(Mapper<SSDDTO, SSD> componentMapper,
                      Mapper<SSDFilterDTO, SSDFilter> componentFilterMapper,
                      ComponentRepository<SSD> componentRepository,
                      PartialUpdate<SSD> partialUpdate,
                      FilterDAO<SSD, SSDFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
