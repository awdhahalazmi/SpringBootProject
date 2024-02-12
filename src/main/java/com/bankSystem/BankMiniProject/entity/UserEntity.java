package com.bankSystem.BankMiniProject.entity;

import com.bankSystem.BankMiniProject.util.enums.Status;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_user")
public class UserEntity {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "account_balance")
    private Double accountBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;


    @OneToMany
    @JoinColumn(name = "user_id")
    private List<BankAccountEntity> bankAccount;
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


    @Column(nullable = false)
    private String password;
    @Column(name = "username",nullable = false)
    private String username;
    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public List<BankAccountEntity> getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(List<BankAccountEntity> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

