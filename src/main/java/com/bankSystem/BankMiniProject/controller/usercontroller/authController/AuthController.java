package com.bankSystem.BankMiniProject.controller.usercontroller.authController;

import com.bankSystem.BankMiniProject.bo.auth.AuthenticationResponse;
import com.bankSystem.BankMiniProject.bo.auth.CreateLoginRequest;
import com.bankSystem.BankMiniProject.bo.auth.CreateUserRequest;
import com.bankSystem.BankMiniProject.bo.auth.LogoutResponse;
import com.bankSystem.BankMiniProject.service.auth.AuthService;
import com.bankSystem.BankMiniProject.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.saveUser(createUserRequest);


        }catch (IllegalArgumentException e){
//            System.out.println("Error please write ACTIVE or INACTIVE");
            return ResponseEntity.badRequest().body("Status should be written either ACTIVE or INACTIVE");
        }
        //userService.saveUser(createUserRequest);
        return ResponseEntity.ok("A User Has Been Created");
    }
    @PostMapping("/login")

    public ResponseEntity<AuthenticationResponse> login(@RequestBody CreateLoginRequest createLoginRequest){
        AuthenticationResponse response = authService.login(createLoginRequest);
        HttpStatus status = HttpStatus.OK;
        if(response == null){
            status = HttpStatus.BAD_REQUEST;}
        return new ResponseEntity<>(response,status);
    }

    @PostMapping("/logout")

    public ResponseEntity<Void> logout(@RequestBody LogoutResponse logoutResponse){
        authService.logout(logoutResponse);
        return new ResponseEntity<>(HttpStatus.OK);}
}

