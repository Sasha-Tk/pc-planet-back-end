package com.pcplanet.pcplanetbackend.UserInformation;

import com.pcplanet.pcplanetbackend.Exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationService {
    private final UserInformationRepository userInformationRepository;

    public List<UserInformation> getAllUsers() {
        return userInformationRepository.findAll();
    }

    public UserInformation getUserById(Long id) {
        return userInformationRepository.findById(id).orElseThrow(()->new NoSuchUserException("User not exist"));
    }

    public Optional<UserInformation> getUserByEmail(String email) {
        return userInformationRepository.findByEmail(email);
    }

    public List<UserInformation> getLongTimeInactiveUsers() {
        return userInformationRepository
                .findUserInformationByLastLoginDateLessThan(
                        LocalDate.now().minusYears(1));
    }

    public List<UserInformation> getInactiveUsers() {
        return userInformationRepository.findUserInformationByLastLoginDateIsNull();
    }
}
