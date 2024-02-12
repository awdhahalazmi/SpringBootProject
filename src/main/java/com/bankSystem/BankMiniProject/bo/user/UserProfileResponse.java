package com.bankSystem.BankMiniProject.bo.user;

import com.bankSystem.BankMiniProject.entity.UserEntity;

import java.util.Optional;
import java.util.stream.Stream;

public class UserProfileResponse {
    private String username;
    private String name;
    private String status;
    private String email;
    private Double accountBalance;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
