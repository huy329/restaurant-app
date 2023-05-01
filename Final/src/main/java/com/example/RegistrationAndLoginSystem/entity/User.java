package com.example.RegistrationAndLoginSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Password should not be empty")
    @Column(nullable = false,unique = true)
    private String email;

    @NotEmpty(message = "Email should not be empty")
    @Column(nullable = false)
    private String password;

    private int status;

    private double balance;

    private double promo;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")})
    private List<Role> roles = new ArrayList<>();
}
