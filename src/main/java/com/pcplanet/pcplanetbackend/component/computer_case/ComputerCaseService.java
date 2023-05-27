package com.pcplanet.pcplanetbackend.component.computer_case;

import com.pcplanet.pcplanetbackend.component.ComponentRepository;
import com.pcplanet.pcplanetbackend.component.ComponentService;
import com.pcplanet.pcplanetbackend.component.FilterDAO;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilter;
import com.pcplanet.pcplanetbackend.component.computer_case.filter.ComputerCaseFilterDTO;
import com.pcplanet.pcplanetbackend.component.mapper.Mapper;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistoryService;
import com.pcplanet.pcplanetbackend.store.StoreService;
import com.pcplanet.pcplanetbackend.utils.PartialUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerCaseService extends ComponentService<ComputerCase, ComputerCaseDTO, ComputerCaseFilter, ComputerCaseFilterDTO> {
    @Autowired
    public ComputerCaseService(Mapper<ComputerCaseDTO, ComputerCase> componentMapper,
                      Mapper<ComputerCaseFilterDTO, ComputerCaseFilter> componentFilterMapper,
                      ComponentRepository<ComputerCase> componentRepository,
                      PartialUpdate<ComputerCase> partialUpdate,
                      FilterDAO<ComputerCase, ComputerCaseFilter> filterDAO,
                      StoreService storeService,
                      PriceHistoryService priceHistoryService) {
        super(componentMapper, componentFilterMapper, componentRepository, partialUpdate, filterDAO, storeService, priceHistoryService);
    }
}
