package com.pcplanet.pcplanetbackend.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Store createNewStoreOrGetExisting(String storeName) {
        return storeRepository.findByName(storeName).orElseGet(() -> storeRepository.save(new Store(storeName)));
    }
}
