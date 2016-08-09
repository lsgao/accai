package com.ruptech.ai.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 2394068598151253815L;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private final String username;
    private final String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
