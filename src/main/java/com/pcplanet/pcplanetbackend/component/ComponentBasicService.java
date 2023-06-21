package com.pcplanet.pcplanetbackend.component;

import com.pcplanet.pcplanetbackend.component.price_history.PriceHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentBasicService {
    private final ComponentBasicRepository componentBasicRepository;

    public Component findComponentById(Long id) {
        return componentBasicRepository.findById(id).orElseThrow();
    }

    public List<PriceHistory> getCurrentOffers(Long id) {
        return findComponentById(id).getPriceHistoryList()
                .stream()
                .filter(priceHistory -> Duration.between(priceHistory.getCheckDate(), Instant.now()).toHours() <= 4)
                .sorted(Comparator.comparing(PriceHistory::getPrice))
                .toList();
    }

    public List<Component> getAllComponentsBySearchValue(String value) {
        return componentBasicRepository.findAllByComponentNameLikeIgnoreCaseOrSkuIgnoreCase("%" + value + "%", value);
    }
}
