package com.pcplanet.pcplanetbackend.component.price_history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pcplanet.pcplanetbackend.store.Store;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Store store;
    private Integer price;
    private Instant checkDate;
    private String href;

    public PriceHistory(Store store, Integer price, String href) {
        this.store = store;
        this.price = price;
        this.checkDate = Instant.now();
        this.href = href;
    }
}
