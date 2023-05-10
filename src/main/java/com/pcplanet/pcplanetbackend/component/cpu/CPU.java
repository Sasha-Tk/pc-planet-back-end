package com.pcplanet.pcplanetbackend.component.cpu;

import com.pcplanet.pcplanetbackend.component.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class CPU extends Component {
    @Column
    private Integer coreCount;

    public CPU(String name, String sku, Integer coreCount) {
        super(name, sku, null);
        this.coreCount = coreCount;
    }
}
