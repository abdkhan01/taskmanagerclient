package com.example.abdullahkhan.task_manager_client.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.abdullahkhan.task_manager_client.R;
import com.example.abdullahkhan.task_manager_client.controller.Api_Functions;
import com.example.abdullahkhan.task_manager_client.network.User_Retrofit;
import com.example.abdullahkhan.task_manager_client.network.RetrofitInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";

    private Toolbar toolbar;

    protected SharedPreferences sharedPref;
    private Api_Functions api_functions;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        setContentView(getLayoutResource());
        api_functions = new Api_Functions(null,this);

        sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        if(!sharedPref.contains("status")){
            Log.d(TAG,"in pefernses");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("status", null);
            editor.apply();

        }
        // Find the toolbar view inside the activity layout
//        View includeToolbar = (View) findViewById(R.id.include_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    protected abstract int getLayoutResource();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.profileMenu:
            {}
            case R.id.logoutMenu:
            {
                progressDialog = new ProgressDialog(Main2Activity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Logging out...");
                progressDialog.show();

                api_functions.logoutUser(sharedPref);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void onLogoutSuccess(){
        this.progressDialog.dismiss();

        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

}
