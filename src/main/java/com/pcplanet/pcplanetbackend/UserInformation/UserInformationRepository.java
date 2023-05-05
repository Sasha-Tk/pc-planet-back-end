package com.pcplanet.pcplanetbackend.UserInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

    Optional<UserInformation> findByEmail(String email);

    List<UserInformation> findUserInformationByLastLoginDateLessThan(LocalDate lastLoginDate);

    List<UserInformation> findUserInformationByLastLoginDateIsNull();

    Boolean existsUserInformationByEmail(String email);
}
