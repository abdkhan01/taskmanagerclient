package com.example.abdullahkhan.task_manager_client.Controller;

import com.example.abdullahkhan.task_manager_client.Model.LoginResponse;
import com.example.abdullahkhan.task_manager_client.Model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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

//    @POST("users/me/avatar")
//    Call<ResponseBody> uploadDp(,
//            @Header("Authorization") String authorization)



}
