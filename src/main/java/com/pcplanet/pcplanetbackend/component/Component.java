package com.pcplanet.pcplanetbackend.component;

import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String componentName;
    @ManyToOne()
    private Vendor vendor;
    private String sku;
    private Integer lowerPrice;

    public Component(String name, String sku, Vendor vendor) {
        this.vendor = vendor;
        this.componentName = name;
        this.sku = sku;
    }
}
