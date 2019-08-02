package com.example.abdullahkhan.task_manager_client.Controller;

import com.example.abdullahkhan.task_manager_client.Model.Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Task_Retrofit {

    @GET("tasks/")
    Call<ArrayList<Task>> getNoticeData();
}
