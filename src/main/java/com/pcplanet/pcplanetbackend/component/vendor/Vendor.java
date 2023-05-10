package com.pcplanet.pcplanetbackend.component.vendor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String vendorName;

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }
}
