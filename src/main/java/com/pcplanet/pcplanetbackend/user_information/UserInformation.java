package com.pcplanet.pcplanetbackend.user_information;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@NoArgsConstructor
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDate registerDate;

    @JsonIgnore
    private LocalDate lastLoginDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonIgnore
    private UserRole userRole;

    public UserInformation(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.registerDate = LocalDate.now();
        this.userRole = UserRole.ROLE_USER;
    }
}
