package com.hb.fatsecret.model;

public class User {
    String email;
    String username;
    String password;
    Purpose purpose;

    public User() {
    }

    public User(String email, String username, String password, Purpose purpose) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.purpose = purpose;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }
}
