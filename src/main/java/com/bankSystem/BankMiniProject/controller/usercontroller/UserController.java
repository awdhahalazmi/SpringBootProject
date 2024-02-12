package com.bankSystem.BankMiniProject.controller.usercontroller;

import com.bankSystem.BankMiniProject.bo.auth.CreateUserRequest;
import com.bankSystem.BankMiniProject.bo.user.UpdateUserRequest;
import com.bankSystem.BankMiniProject.bo.user.UserProfileResponse;
import com.bankSystem.BankMiniProject.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    @PutMapping("/update")

    public  ResponseEntity<String>updateUser(@RequestParam Long userId, @RequestBody UpdateUserRequest updateUserStatusRequest){
        try {
            userService.updateUserStatus(userId, updateUserStatusRequest);


        }catch (IllegalArgumentException e){
//            System.out.println("Error please write ACTIVE or INACTIVE");
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok("A User Has Been Updated");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestParam Long userId){
        UserProfileResponse userInfo = userService.getUserInfo(userId);
        return ResponseEntity.ok(userInfo);
    }
}
