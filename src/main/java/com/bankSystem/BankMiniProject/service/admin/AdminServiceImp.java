package com.bankSystem.BankMiniProject.service.admin;

import com.bankSystem.BankMiniProject.bo.auth.AuthenticationResponse;
import com.bankSystem.BankMiniProject.bo.user.UserProfileResponse;
import com.bankSystem.BankMiniProject.entity.BankAccountEntity;
import com.bankSystem.BankMiniProject.entity.UserEntity;
import com.bankSystem.BankMiniProject.repository.UserRepository;
import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bankSystem.BankMiniProject.util.enums.Roles.user;

@Service
public class AdminServiceImp implements AdminService{
    private final UserRepository userRepository;

    public AdminServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}




