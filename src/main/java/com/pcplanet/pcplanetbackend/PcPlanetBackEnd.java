package com.pcplanet.pcplanetbackend;

import com.pcplanet.pcplanetbackend.component.cpu.CPURepository;
import com.pcplanet.pcplanetbackend.component.gpu.GPURepository;
import com.pcplanet.pcplanetbackend.component.gpu.output_interface.GPUOutputInterfaceRepository;
import com.pcplanet.pcplanetbackend.user_information.UserInformation;
import com.pcplanet.pcplanetbackend.user_information.UserInformationRepository;
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
    public CommandLineRunner commandLineRunner(
            UserInformationRepository userInformationRepository,
            GPURepository gpuRepository,
            CPURepository cpuRepository,
            GPUOutputInterfaceRepository gpuOutputInterfaceRepository
    ) {
        return args -> {
            userInformationRepository.deleteAll();
            gpuRepository.deleteAll();
            gpuOutputInterfaceRepository.deleteAll();
            cpuRepository.deleteAll();
            gpuRepository.deleteAll();
            UserInformation u = new UserInformation("example1@gmail.com", "Shadow", new BCryptPasswordEncoder().encode("1234"));
            u.setLastLoginDate(LocalDate.of(2022, 5, 1));
            userInformationRepository.save(u);
            userInformationRepository.save(new UserInformation("example2@gmail.com", "Shadow", new BCryptPasswordEncoder().encode("1234")));
        };
    }
}
