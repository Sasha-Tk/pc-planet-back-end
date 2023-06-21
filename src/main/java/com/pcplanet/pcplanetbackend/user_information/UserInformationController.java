package com.pcplanet.pcplanetbackend.user_information;

import com.pcplanet.pcplanetbackend.build.Build;
import com.pcplanet.pcplanetbackend.build.BuildDTO;
import com.pcplanet.pcplanetbackend.component.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserInformationController {

    private final UserInformationService userInformationService;

    @GetMapping
    public ResponseEntity<List<UserInformation>> getAllUsers() {
        return ResponseEntity.ok(this.userInformationService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserInformation> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userInformationService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserInformation> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userInformationService.getUserByEmail(email));
    }

    @GetMapping("/long-time-inactive")
    public ResponseEntity<List<UserInformation>> getLongTimeInactiveUsers() {
        return ResponseEntity.ok(this.userInformationService.getLongTimeInactiveUsers());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<UserInformation>> getInactiveUsers() {
        return ResponseEntity.ok(this.userInformationService.getInactiveUsers());
    }

    @PostMapping("/{userId}/favorites/{componentId}")
    public ResponseEntity<List<Component>> addComponentToUserFavorites(@PathVariable Long userId, @PathVariable Long componentId) {
        return ResponseEntity.ok(this.userInformationService.addComponentToUserFavorites(userId, componentId));
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<List<Component>> getUserFavorites(@PathVariable Long id) {
        return ResponseEntity.ok(this.userInformationService.getUserFavorites(id));
    }

    @DeleteMapping("/{userId}/favorites/{componentId}")
    public ResponseEntity<List<Component>> deleteComponentFromUserFavorites(@PathVariable Long userId, @PathVariable Long componentId) {
        return ResponseEntity.ok(this.userInformationService.deleteComponentFromUserFavorites(userId, componentId));
    }

    @PostMapping("/{id}/builds")
    public ResponseEntity<List<Build>> addUserBuild(@PathVariable Long id, @RequestBody BuildDTO buildDTO) {
        return ResponseEntity.ok(this.userInformationService.addUserBuild(id, buildDTO));
    }

    @PatchMapping("/{id}/builds")
    public ResponseEntity<Build> updateUserBuild(@PathVariable Long id, @RequestBody BuildDTO buildDTO) {
        return ResponseEntity.ok(this.userInformationService.updateUserBuild(buildDTO));
    }

    @GetMapping("/{id}/builds")
    public ResponseEntity<List<Build>> getUserBuilds(@PathVariable Long id) {
        return ResponseEntity.ok(this.userInformationService.getAllUserBuilds(id));
    }

    @DeleteMapping("/{userId}/builds/{buildId}")
    public ResponseEntity<List<Build>> deleteUserBuild(@PathVariable Long userId, @PathVariable Long buildId) {
        return ResponseEntity.ok(this.userInformationService.deleteBuild(userId, buildId));
    }


//    @ExceptionHandler(NoSuchUserException.class)
//    public ResponseEntity<ExceptionMessage> handleException(NoSuchUserException e) {
//        return new ResponseEntity<>(new ExceptionMessage("No element present Локальний хендлер в юзер контроллері", e.getMessage()), HttpStatus.UNAUTHORIZED);
//    }
}
