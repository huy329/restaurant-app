package com.example.RegistrationAndLoginSystem.service;

import com.example.RegistrationAndLoginSystem.entity.Role;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.repository.RoleRepository;
import com.example.RegistrationAndLoginSystem.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        Role roleCheck = roleRepository.findByName("ROLE_USER");
        if(roleCheck == null){
            Role roleSet = new Role();
            roleSet.setName("ROLE_USER");
            roleRepository.save(roleSet);
            user.setRoles(Arrays.asList(roleSet));
        } else {
            user.setRoles(Arrays.asList(roleCheck));
        }
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUser() {
        List<User> user = userRepository.findAll();
        return user;
    }

    public void userStatusStop(User user) {
        user.setStatus(0);
        userRepository.save(user);
    }
}