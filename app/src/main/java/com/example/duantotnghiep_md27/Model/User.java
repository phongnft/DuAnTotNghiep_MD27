package com.example.duantotnghiep_md27.Model;

public class User {
    String id, name, email, password, token, firebase_token;


    public User(String name, String email, String password, String firebase_token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.firebase_token = firebase_token;
    }
    public User(String id, String name, String email, String password, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public User(String email, String password, String firebase_token) {
        this.email = email;
        this.password = password;
        this.firebase_token = firebase_token;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirebase_token() {
        return firebase_token;
    }

    public void setFirebase_token(String firebase_token) {
        this.firebase_token = firebase_token;
    }


}
