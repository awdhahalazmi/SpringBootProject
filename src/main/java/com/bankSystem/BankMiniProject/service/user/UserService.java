package com.bankSystem.BankMiniProject.service.user;

import com.bankSystem.BankMiniProject.bo.auth.CreateUserRequest;
import com.bankSystem.BankMiniProject.bo.user.UpdateUserRequest;
import com.bankSystem.BankMiniProject.bo.user.UserProfileResponse;

import java.util.List;

public interface UserService {
        void saveUser(CreateUserRequest createUserRequest);
        void updateUserStatus(Long userId, UpdateUserRequest updateUserStatusRequest);
        void deleteUserById(Long userId );
        List<String> getALlUsersWithStrongPassword();

        List<UserProfileResponse> getAllUserProfiles();
        UserProfileResponse getUserInfo(Long userId);



}

