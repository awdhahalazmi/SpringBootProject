package com.bankSystem.BankMiniProject.repository;

import com.bankSystem.BankMiniProject.entity.BankAccountEntity;
import com.bankSystem.BankMiniProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
}
