package com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MotherboardFormFactorRepository extends JpaRepository<MotherboardFormFactor, Short> {
    boolean existsByFormFactorName(String formFactorName);
    Optional<MotherboardFormFactor> findByFormFactorName(String motherboardFormFactorName);
}
