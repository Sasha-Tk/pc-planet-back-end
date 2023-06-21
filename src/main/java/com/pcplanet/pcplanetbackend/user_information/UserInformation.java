package com.pcplanet.pcplanetbackend.user_information;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcplanet.pcplanetbackend.build.Build;
import com.pcplanet.pcplanetbackend.build.BuildRepository;
import com.pcplanet.pcplanetbackend.component.Component;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.objenesis.SpringObjenesis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    private List<Build> builds;

    @OneToMany
    private List<Component> favorites;

    public UserInformation(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.registerDate = LocalDate.now();
        this.userRole = UserRole.ROLE_USER;
        this.builds = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }

    public void addBuild(Build build){
        builds.add(build);
    }

    public void removeBuild(Build build){
        builds.remove(build);
    }

    public void addToFavorites(Component component){
        favorites.add(component);
    }

    public void removeFromFavorites(Component component){
        favorites.remove(component);
    }
}
