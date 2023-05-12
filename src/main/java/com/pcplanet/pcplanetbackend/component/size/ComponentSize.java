package com.pcplanet.pcplanetbackend.component.size;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ComponentSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float width;
    private Float depth;
    private Float height;

    public ComponentSize(Float width, Float depth, Float height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }
}
