package com.pcplanet.pcplanetbackend.component.ram;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilter;
import com.pcplanet.pcplanetbackend.component.ram.filter.RAMFilterDTO;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RAMService extends ComponentService<RAM, RAMDTO, RAMFilter, RAMFilterDTO> {
    @Autowired
    public RAMService(Mapper<RAMDTO, RAM> componentMapper,
                      Mapper<RAMFilterDTO, RAMFilter> componentFilterMapper,
                      ComponentRepository<RAM> componentRepository,
                      PartialUpdate<RAM> partialUpdate,
                      FilterDAO<RAM, RAMFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
