package com.kunitskaya.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Scope("session")
public class User {
    @NotNull(message = "Username is required")
    @Size(min = 1, max = 30)
    private String username;
    @NotNull(message = "Username is required")
    @Size(min = 1, max = 30)
    private String password;
    private UserRoles role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
