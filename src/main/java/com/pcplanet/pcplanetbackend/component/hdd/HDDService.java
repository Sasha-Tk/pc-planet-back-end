package com.pcplanet.pcplanetbackend.component.hdd;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.hdd.filter.HDDFilter;
import com.pcplanet.pcplanetbackend.component.hdd.filter.HDDFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HDDService extends ComponentService<HDD, HDDDTO, HDDFilter, HDDFilterDTO> {
    @Autowired
    public HDDService(Mapper<HDDDTO, HDD> componentMapper,
                      Mapper<HDDFilterDTO, HDDFilter> componentFilterMapper,
                      ComponentRepository<HDD> componentRepository,
                      PartialUpdate<HDD> partialUpdate,
                      FilterDAO<HDD, HDDFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
