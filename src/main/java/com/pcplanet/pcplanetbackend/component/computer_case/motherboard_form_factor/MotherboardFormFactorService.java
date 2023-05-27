package com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor;

import com.pcplanet.pcplanetbackend.exception.component_exception.gpu_exception.NoSuchOutputInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotherboardFormFactorService {
    private final MotherboardFormFactorRepository motherboardFormFactorRepository;

    public boolean motherboardFormFactorAlreadyExist(String formFactorName) {
        return motherboardFormFactorRepository.existsByFormFactorName(formFactorName);
    }

    public MotherboardFormFactor createMotherboardFormFactor(String formFactorName) {
        return motherboardFormFactorRepository.save(new MotherboardFormFactor(formFactorName));
    }

    //TODO: create NoSuchMotherboardFormFactor
    public MotherboardFormFactor findFormFactorByFormFactorName(String formFactorName) {
        return motherboardFormFactorRepository
                .findByFormFactorName(formFactorName)
                .orElseThrow(() -> new NoSuchOutputInterface("No motherboard form factor with this name"));
    }

    public MotherboardFormFactor createFormFactorOrGetExisting(String formFactorName) {
//        MotherboardFormFactor createdFormFactor;
        return motherboardFormFactorAlreadyExist(formFactorName) ?
                        findFormFactorByFormFactorName(formFactorName) :
                        createMotherboardFormFactor(formFactorName);
    }

    public List<MotherboardFormFactor> createFormFactorsOrGetExisting(List<String> formFactorsNames) {
        List<MotherboardFormFactor> createdFormFactors = new ArrayList<>();
        formFactorsNames.forEach(formFactorName ->
                createdFormFactors.add(motherboardFormFactorAlreadyExist(formFactorName) ?
                        findFormFactorByFormFactorName(formFactorName) :
                        createMotherboardFormFactor(formFactorName)));
        return createdFormFactors;
    }
}
