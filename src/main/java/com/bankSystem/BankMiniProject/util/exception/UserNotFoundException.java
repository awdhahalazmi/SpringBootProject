package com.bankSystem.BankMiniProject.util.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String str) {
        super(str);
    }
}
