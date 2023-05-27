package com.pcplanet.pcplanetbackend.component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistory;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String componentName;
    @ManyToOne
    private Vendor vendor;
    private String sku;
    private Float width;
    private Float depth;
    private Float height;

    @Transient
    private Integer lowerPrice;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistory> priceHistoryList;
    private String imageURL;

    public Component(String name, String sku, Vendor vendor, Float width, Float depth, Float height, String imageURL) {
        this.vendor = vendor;
        this.componentName = name;
        this.sku = sku;
        this.imageURL = imageURL;
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    public Integer getLowerPrice() {
        if (priceHistoryList != null) {
            List<PriceHistory> actualPrice = priceHistoryList.stream()
                    .filter(priceHistory -> priceHistory.getCheckDate().equals(LocalDate.now()))
                    .toList();
            Optional<PriceHistory> minPrice = actualPrice.stream().min(Comparator.comparing(PriceHistory::getPrice));
            this.lowerPrice = minPrice.map(PriceHistory::getPrice).orElse(null);
        }
        return lowerPrice;
    }

    public void addPriceHistory(PriceHistory priceHistory) {
        if (this.priceHistoryList == null) {
            this.priceHistoryList = new ArrayList<>();
        }
        this.priceHistoryList.add(priceHistory);
    }
}
