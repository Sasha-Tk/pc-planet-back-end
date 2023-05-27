package com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MotherboardFormFactor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String formFactorName;

    public MotherboardFormFactor(String formFactorName) {
        this.formFactorName = formFactorName;
    }
}
