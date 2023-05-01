package com.example.RegistrationAndLoginSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RegistrationAndLoginSystem.entity.TableR;

@Repository
public interface TableRepository extends JpaRepository<TableR,Long> {
}

