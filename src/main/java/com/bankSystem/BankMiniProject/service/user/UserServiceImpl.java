package com.bankSystem.BankMiniProject.service.user;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.bankSystem.BankMiniProject.bo.auth.CreateUserRequest;
import com.bankSystem.BankMiniProject.bo.user.UpdateUserRequest;
import com.bankSystem.BankMiniProject.bo.user.UserProfileResponse;
import com.bankSystem.BankMiniProject.entity.BankAccountEntity;
import com.bankSystem.BankMiniProject.entity.UserEntity;
import com.bankSystem.BankMiniProject.repository.BankAccountRepository;
import com.bankSystem.BankMiniProject.repository.UserRepository;
import com.bankSystem.BankMiniProject.util.enums.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private BankAccountRepository bankAccountRepository;



    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
        if (!createUserRequest.getStatus().equals(Status.ACTIVE.name()) || !createUserRequest.getStatus().equals(Status.INACTIVE.name())) {
            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");

        }
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUserStatus(Long userId, UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow();
        if(!updateUserRequest.getStatus().equals("ACTIVE") && !updateUserRequest.getStatus().equals("INACTIVE")){
            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");

        }
        userEntity.setStatus(Status.valueOf(updateUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<String> getALlUsersWithStrongPassword() {
        return userRepository.findAll().stream()
                .filter(user -> user.getPassword().length() > 8)
                .map(UserEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserProfileResponse> getAllUserProfiles() {
        return null;
    }

    @Override
    public UserProfileResponse getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow();
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setEmail(userEntity.getEmail());
        userProfileResponse.setName(userEntity.getName());
        userProfileResponse.setUsername(userEntity.getUsername());
        userProfileResponse.setAccountBalance(userEntity.getAccountBalance());
        return userProfileResponse;
    }


}

