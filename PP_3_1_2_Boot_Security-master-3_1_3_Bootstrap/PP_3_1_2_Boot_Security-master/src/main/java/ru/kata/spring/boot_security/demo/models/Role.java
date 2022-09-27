package ru.kata.spring.boot_security.demo.models;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;



@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String role;

    public Role(String role) {
        this.role = role;
    }
    public Role() {
    }


    @Override
    public String getAuthority() {
        return "ROLE_" + this.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
