package com.pcplanet.pcplanetbackend.component.price_history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pcplanet.pcplanetbackend.store.Store;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Store store;
    private Integer price;
    private LocalDate checkDate;

    public PriceHistory(Store store, Integer price) {
        this.store = store;
        this.price = price;
        this.checkDate = LocalDate.now();
    }
}
