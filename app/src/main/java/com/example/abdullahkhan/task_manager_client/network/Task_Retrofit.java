package com.example.abdullahkhan.task_manager_client.controller;

import com.example.abdullahkhan.task_manager_client.model.Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Task_Retrofit {

    @GET("/tasks")
    Call<ArrayList<Task>> getTasks(@Query("sort") String sort, @Query("completed") String completed,
                                   @Header("Authorization") String authorization);

    @POST("/tasks")
    Call<Task> saveTask(@Body Task task,@Header("Authorization") String authorization);

    @GET("/tasks/{id}")
    Call<Task> findTask(@Path("id") String taskId,@Header("Authorization") String authorization);

    @PATCH("/tasks/{id}")
    Call<Task> updateTask(@Field("completed") String completed, @Field("description") String description,
                          @Path("id") String taskId,
                          @Header("Authorization") String authorization);

    @DELETE("/tasks/{id}")
    Call<Task> deleteTask(@Path("id") String taskId,@Header("Authorization") String authorization);

}
