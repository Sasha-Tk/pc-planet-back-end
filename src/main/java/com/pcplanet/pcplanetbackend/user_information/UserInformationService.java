package com.pcplanet.pcplanetbackend.user_information;

import com.pcplanet.pcplanetbackend.build.Build;
import com.pcplanet.pcplanetbackend.build.BuildDTO;
import com.pcplanet.pcplanetbackend.build.BuildService;
import com.pcplanet.pcplanetbackend.component.Component;
import com.pcplanet.pcplanetbackend.component.ComponentBasicService;
import com.pcplanet.pcplanetbackend.exception.user_exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserInformationService {
    private final UserInformationRepository userInformationRepository;
    private final BuildService buildService;
    private final ComponentBasicService componentBasicService;

    public List<UserInformation> getAllUsers() {
        return userInformationRepository.findAll();
    }

    public UserInformation getUserById(Long id) {
        return userInformationRepository.findById(id).orElseThrow(() -> new NoSuchUserException("User not exist"));
    }

    public UserInformation getUserByEmail(String email) {
        return userInformationRepository.findByEmail(email).orElseThrow(() -> new NoSuchUserException("No user with this email"));
    }

    public List<UserInformation> getLongTimeInactiveUsers() {
        return userInformationRepository
                .findUserInformationByLastLoginDateLessThan(
                        LocalDate.now().minusYears(1));
    }

    public List<UserInformation> getInactiveUsers() {
        return userInformationRepository.findUserInformationByLastLoginDateIsNull();
    }

    public List<Component> getUserFavorites(Long id) {
        return getUserById(id).getFavorites();
    }

    public List<Component> addComponentToUserFavorites(Long userId, Long componentId) {
        UserInformation userInformation = getUserById(userId);
        Component component = componentBasicService.findComponentById(componentId);
        userInformation.addToFavorites(component);
        return userInformationRepository.save(userInformation).getFavorites();
    }

    public List<Component> deleteComponentFromUserFavorites(Long userId, Long componentId) {
        UserInformation userInformation = getUserById(userId);
        Component component = componentBasicService.findComponentById(componentId);
        userInformation.removeFromFavorites(component);
        userInformation = userInformationRepository.save(userInformation);
        return userInformationRepository.save(userInformation).getFavorites();
    }

    public List<Build> getAllUserBuilds(Long id) {
        return getUserById(id).getBuilds();
    }

    public List<Build> addUserBuild(Long id, BuildDTO buildDTO) {
        UserInformation userInformation = getUserById(id);
        userInformation.addBuild(buildService.createBuild(buildDTO));
        return userInformationRepository.save(userInformation).getBuilds();
    }

    public List<Build> deleteBuild(Long userId, Long buildId) {
        UserInformation userInformation = getUserById(userId);
        Build build = buildService.findBuildById(buildId);
        userInformation.removeBuild(build);
        userInformation = userInformationRepository.save(userInformation);
        buildService.deleteById(buildId);
        return userInformation.getBuilds();
    }

    public Build updateUserBuild(BuildDTO buildDTO) {
        return buildService.updateBuild(buildDTO);
    }
}
