package com.pcplanet.pcplanetbackend.component.cpu_fan;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilter;
import com.pcplanet.pcplanetbackend.component.cpu_fan.filter.CPUFanFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPUFanService extends ComponentService<CPUFan, CPUFanDTO, CPUFanFilter, CPUFanFilterDTO> {
    @Autowired
    public CPUFanService(Mapper<CPUFanDTO, CPUFan> componentMapper,
                         Mapper<CPUFanFilterDTO, CPUFanFilter> componentFilterMapper,
                         ComponentRepository<CPUFan> componentRepository,
                         PartialUpdate<CPUFan> partialUpdate,
                         FilterDAO<CPUFan, CPUFanFilter> filterDAO,
                         StoreService storeService,
                         PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
