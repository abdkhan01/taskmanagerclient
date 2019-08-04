package com.example.abdullahkhan.task_manager_client.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("user")
    private User user;
    @SerializedName("token")
    private String token;

    public LoginResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
