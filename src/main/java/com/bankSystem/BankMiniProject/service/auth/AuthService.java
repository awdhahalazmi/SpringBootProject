package com.bankSystem.BankMiniProject.service.auth;

import com.bankSystem.BankMiniProject.bo.auth.AuthenticationResponse;
import com.bankSystem.BankMiniProject.bo.auth.CreateLoginRequest;
import com.bankSystem.BankMiniProject.bo.auth.LogoutResponse;
import com.bankSystem.BankMiniProject.bo.user.CreateSignUpRequest;

public interface AuthService {
    void signup(CreateSignUpRequest createSignupRequest);


    AuthenticationResponse login(CreateLoginRequest createLoginRequest);
    void logout(LogoutResponse logoutResponse);
}
