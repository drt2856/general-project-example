package com.cu.generalprojectexample.dto;

import com.cu.generalprojectexample.model.User;

public class Userpass {
    private String username;
    private String password;

    public Userpass() {
    }

    public Userpass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Userpass(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
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
}
