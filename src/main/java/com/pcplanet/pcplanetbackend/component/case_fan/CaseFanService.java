package com.pcplanet.pcplanetbackend.component.case_fan;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilter;
import com.pcplanet.pcplanetbackend.component.case_fan.filter.CaseFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseFanService extends ComponentService<CaseFan, CaseFanDTO, CaseFanFilter, CaseFanFilterDTO> {
    @Autowired
    public CaseFanService(Mapper<CaseFanDTO, CaseFan> componentMapper,
                          Mapper<CaseFanFilterDTO, CaseFanFilter> componentFilterMapper,
                          ComponentRepository<CaseFan> componentRepository,
                          PartialUpdate<CaseFan> partialUpdate,
                          FilterDAO<CaseFan, CaseFanFilter> filterDAO,
                          StoreService storeService,
                          PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
