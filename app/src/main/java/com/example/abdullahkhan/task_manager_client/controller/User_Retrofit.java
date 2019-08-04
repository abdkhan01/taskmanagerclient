package com.example.abdullahkhan.task_manager_client.controller;

import android.media.Image;

import com.example.abdullahkhan.task_manager_client.model.LoginResponse;
import com.example.abdullahkhan.task_manager_client.model.User;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface User_Retrofit {

    @POST("/users")
    Call<LoginResponse> postUser(@Body User user);

    @POST("/users/login")
    Call<LoginResponse> loginUser(@Field("email") String email, @Field("password") String password);

    @POST("/users/logout")
    Call<ResponseBody> logoutUser(@Header("Authorization") String authorization);

    @POST("/users/logoutAll")
    Call<ResponseBody> logoutAllUsers(@Header("Authorization") String authorization);

    @GET("/users/me")
    Call<User> myAccount(@Header("Authorization") String authorization);

    @POST("/users/me/avatar")
    Call<ResponseBody> uploadAvatar(@Part MultipartBody.Part part,
            @Header("Authorization") String authorization);

    @DELETE("/users/me/avatar")
    Call<ResponseBody> deleteMyAvatar(@Header("Authorization") String authorization);

    @GET("/users/{id}/avatar")
    Call<Image> deleteAvatar(@Path("id") String id,
                             @Header("Authorization") String authorization);

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") String id,
                             @Header("Authorization") String authorization);

    @PATCH("/users/me")
    Call<User> updateMe(@Body User user,
            @Header("Authorization") String authorization);

    @DELETE("/users/me")
    Call<User> deleteMe(@Header("Authorization") String authorization);



}
