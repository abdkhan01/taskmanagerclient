package com.example.abdullahkhan.task_manager_client.model;

import com.google.gson.annotations.SerializedName;

import java.nio.Buffer;

public class User {

    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("age")
    private int age;
    @SerializedName("avatar")
    private Buffer avatar;
    @SerializedName("token")
    private String token;


    public User(String name, String email, String password, int age, Buffer avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.avatar = avatar;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Buffer getAvatar() {
        return avatar;
    }

    public void setAvatar(Buffer avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
