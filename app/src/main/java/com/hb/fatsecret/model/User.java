package com.hb.fatsecret.model;

public class User {
    String email;
    String username;
    String password;
    Information information;

    public User() {
    }

    public User(String email, String username, String password, Information information) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.information = information;
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

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}
