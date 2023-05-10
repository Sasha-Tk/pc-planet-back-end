package com.pcplanet.pcplanetbackend.user_information;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

//    @ExceptionHandler(NoSuchUserException.class)
//    public ResponseEntity<ExceptionMessage> handleException(NoSuchUserException e) {
//        return new ResponseEntity<>(new ExceptionMessage("No element present Локальний хендлер в юзер контроллері", e.getMessage()), HttpStatus.UNAUTHORIZED);
//    }
}
