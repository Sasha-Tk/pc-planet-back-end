package com.pcplanet.pcplanetbackend;

import com.pcplanet.pcplanetbackend.UserInformation.UserInformation;
import com.pcplanet.pcplanetbackend.UserInformation.UserInformationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class PcPlanetBackEnd {


    public static void main(String[] args) {
        SpringApplication.run(PcPlanetBackEnd.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(UserInformationRepository userInformationRepository) {
        return args -> {
            UserInformation u = new UserInformation("example1@gmail.com", "Shadow", new BCryptPasswordEncoder().encode("1234"));
            u.setLastLoginDate(LocalDate.of(2022, 5, 1));
            userInformationRepository.save(u);
            userInformationRepository.save(new UserInformation("example2@gmail.com", "Shadow", new BCryptPasswordEncoder().encode("1234")));
        };
    }
}
