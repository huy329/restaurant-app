package com.example.RegistrationAndLoginSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RegistrationAndLoginSystem.entity.Deposit;
import com.example.RegistrationAndLoginSystem.entity.User;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {
    List<Deposit> findByUser(User user);
}
