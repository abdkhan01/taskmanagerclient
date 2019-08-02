package com.example.abdullahkhan.task_manager_client.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abdullahkhan.task_manager_client.R;

public class FragmentActivity extends AppCompatActivity {
    private String description;
    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        description = (String) getIntent().getExtras().getSerializable("description");
        time = (String) getIntent().getExtras().getSerializable("time");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TaskFragment taskFragment = TaskFragment.newInstance(description,time);
        ft.replace(R.id.placeholder, taskFragment);
        ft.commit();

    }
}
