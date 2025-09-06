package com;

public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private UserRole role;

    public User(Long id, String username, String password, String email, String fullName, String address, String phone, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }
}
