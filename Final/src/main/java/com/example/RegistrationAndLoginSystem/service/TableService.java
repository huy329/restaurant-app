package com.example.RegistrationAndLoginSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.repository.TableRepository;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    public void createTable(TableR table) {
        tableRepository.save(table);
    }
    
    public void updateTable(TableR table) {
        tableRepository.save(table);
    }
    
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    public List<TableR> findAllTable() {
        List<TableR> table = tableRepository.findAll();
        return table;
    }

    public Optional<TableR> findById(Long tableId) {
        return tableRepository.findById(tableId);
    }
}
