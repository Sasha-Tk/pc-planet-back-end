package com.pcplanet.pcplanetbackend.component.price_history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceHistoryService {
    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistory createPriceHistory(PriceHistory priceHistory){
        return priceHistoryRepository.save(priceHistory);
    }
}
