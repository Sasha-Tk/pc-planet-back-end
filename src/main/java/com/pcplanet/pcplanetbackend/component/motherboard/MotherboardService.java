package com.pcplanet.pcplanetbackend.component.motherboard;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.motherboard.Motherboard;
import com.pcplanet.pcplanetbackend.component.motherboard.MotherboardDTO;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilter;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilterDTO;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotherboardService extends ComponentService<Motherboard, MotherboardDTO, MotherboardFilter, MotherboardFilterDTO> {
    @Autowired
    public MotherboardService(Mapper<MotherboardDTO, Motherboard> componentMapper,
                      Mapper<MotherboardFilterDTO, MotherboardFilter> componentFilterMapper,
                      ComponentRepository<Motherboard> componentRepository,
                      PartialUpdate<Motherboard> partialUpdate,
                      FilterDAO<Motherboard, MotherboardFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
