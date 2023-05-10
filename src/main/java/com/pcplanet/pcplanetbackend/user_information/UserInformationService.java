package com.pcplanet.pcplanetbackend.user_information;

import com.pcplanet.pcplanetbackend.exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public UserInformation getUserByEmail(String email) {
        return userInformationRepository.findByEmail(email).orElseThrow(()->new NoSuchUserException("No user with this email"));
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
