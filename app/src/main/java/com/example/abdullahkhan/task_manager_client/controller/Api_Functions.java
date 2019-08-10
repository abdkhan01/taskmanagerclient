package com.example.abdullahkhan.task_manager_client.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.abdullahkhan.task_manager_client.model.LoginResponse;
import com.example.abdullahkhan.task_manager_client.model.Task;
import com.example.abdullahkhan.task_manager_client.model.User;
import com.example.abdullahkhan.task_manager_client.network.RetrofitInstance;
import com.example.abdullahkhan.task_manager_client.network.Task_Retrofit;
import com.example.abdullahkhan.task_manager_client.network.User_Retrofit;
import com.example.abdullahkhan.task_manager_client.view.FragmentActivity;
import com.example.abdullahkhan.task_manager_client.view.LoginActivity;
import com.example.abdullahkhan.task_manager_client.view.Main2Activity;
import com.example.abdullahkhan.task_manager_client.view.MainActivity;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Api_Functions {
    private static final String TAG = "Api-Functions";
    private User loggedInUser;

    private LoginActivity loginActivity;
    private Main2Activity main2Activity;
    private MainActivity mainActivity;
    private FragmentActivity fragmentActivity;

    public Api_Functions(LoginActivity loginActivity, Main2Activity main2Activity) {
        this.loginActivity = loginActivity;
        this.main2Activity = main2Activity;
    }

    public Api_Functions(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public Api_Functions(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }



    public Api_Functions() {
    }

    public void loginUser(String email, String password, SharedPreferences sharedPref){
        loggedInUser = new User(email,password);

        User_Retrofit service = RetrofitInstance.getRetrofitInstance().create(User_Retrofit.class);

        Call<LoginResponse> call = service.loginUser(loggedInUser);

        Log.d(TAG,"enque main ja raha hai");
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG,"On response main hai");

                if(response.isSuccessful()){
                    Log.d(TAG,"On response successful main hai");
                    String code = Integer.toString(response.code());
                    Log.d(TAG,code);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", response.body().getToken());
                    editor.putString("status", "loggedin");
                    editor.apply();
                    onLoginSuccess();

                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG,"On faliure main hai"+t.getMessage());
                onLogoutFaliure();
            }
        });
    }
    private void onLoginSuccess(){
        this.loginActivity.onLoginSuccess();
    }

    private void onLogoutFaliure(){
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.onLoginFailed();
    }
    public void logoutUser(SharedPreferences sharedPref){
        User_Retrofit service = RetrofitInstance.getRetrofitInstance().create(User_Retrofit.class);
        String token = sharedPref.getString("token","null");

        Call<ResponseBody> call = service.logoutUser("Bearer ".concat(token));

        Log.d(TAG,"enque main ja raha hai"+token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"On response main hai");

                if(response.isSuccessful()){
                    Log.d(TAG,"On response successful main hai");
                    String code = Integer.toString(response.code());
                    Log.d(TAG,code);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("status",null);
                    editor.putString("token",null);
                    editor.apply();
                    onLogoutSuccess();

                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"On faliure main hai"+t.getMessage());

            }
        });
    }

    private void onLogoutSuccess(){
        this.main2Activity.onLogoutSuccess();

    }

    public void getTasks(SharedPreferences sharedPreferences){

        Task_Retrofit service = RetrofitInstance.getRetrofitInstance().create(Task_Retrofit.class);
        String token = sharedPreferences.getString("token","null");

        if(!(token.equals("null"))){

            Call<ArrayList<Task>> call = service.getTasks(null,null,"Bearer ".concat(token));

            Log.d(TAG,"enque main ja raha hai get tasks");
            call.enqueue(new Callback<ArrayList<Task>>() {
                @Override
                @EverythingIsNonNull
                public void onResponse(Call<ArrayList<Task>> call, Response<ArrayList<Task>> response) {

                    if(response.body() != null){
                        onGetTasksSuccessful(response.body());

                    }
                }

                @Override
                @EverythingIsNonNull
                public void onFailure(Call<ArrayList<Task>> call, Throwable t) {

                }
            });
        }
        else{
            Log.d(TAG,"else get tasks");
        }

    }

    private void onGetTasksSuccessful(ArrayList<Task> tasks){
        this.mainActivity.createRecyclerViews(tasks);
    }

    public void postTask(String description){
        Log.d(TAG,"post task testing");
    }

}
