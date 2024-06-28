package com.lamnguyen.server.models.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private boolean lock;

    // Constructors, getters, and setters
//    public AccountDTO() {}
//
//    public AccountDTO(Long id, String name, String phone, String email, String password, boolean lock) {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.password = password;
//        this.lock = lock;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isLock() {
//        return lock;
//    }
//
//    public void setLock(boolean lock) {
//        this.lock = lock;
//    }
}