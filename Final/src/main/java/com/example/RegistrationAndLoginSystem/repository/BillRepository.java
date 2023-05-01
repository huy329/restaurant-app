package com.example.RegistrationAndLoginSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RegistrationAndLoginSystem.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
}
