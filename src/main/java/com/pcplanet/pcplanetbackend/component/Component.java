package com.pcplanet.pcplanetbackend.component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcplanet.pcplanetbackend.component.price_history.PriceHistory;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ComponentType componentType;
    private String componentName;
    @ManyToOne
    private Vendor vendor;
    private String sku;
    private Float width;
    private Float depth;
    private Float height;

    private Integer lowerPrice;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistory> priceHistoryList;
    private String imageURL;

    public Component(ComponentType componentType, String name, String sku, Vendor vendor, Float width, Float depth, Float height, String imageURL) {
        this.componentType = componentType;
        this.componentName = name;
        this.sku = sku;
        this.vendor = vendor;
        this.imageURL = imageURL;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.priceHistoryList = new ArrayList<>();
    }

    public void addPriceHistory(PriceHistory priceHistory) {
        if (this.priceHistoryList == null) {
            this.priceHistoryList = new ArrayList<>();
        }
        this.priceHistoryList.add(priceHistory);
    }
}
