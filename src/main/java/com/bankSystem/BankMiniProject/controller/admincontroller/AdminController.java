package com.bankSystem.BankMiniProject.controller.admincontroller;

import com.bankSystem.BankMiniProject.bo.user.UserProfileResponse;
import com.bankSystem.BankMiniProject.entity.UserEntity;
import com.bankSystem.BankMiniProject.service.admin.AdminService;
import com.bankSystem.BankMiniProject.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    private final AdminService adminService;

    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/get_users")
    public List<UserEntity> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long id){

        UserProfileResponse userProfileResponse= userService.getUserInfo(id);

        return ResponseEntity.ok(userProfileResponse);
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("user has been deleted successfully ");
    }
}

