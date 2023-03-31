package com.university.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles roles;

    public Users(String email, String password, String userName, Roles roles) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
