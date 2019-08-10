package com.example.abdullahkhan.task_manager_client.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.example.abdullahkhan.task_manager_client.R;
import com.example.abdullahkhan.task_manager_client.controller.Api_Functions;

public class FragmentActivity extends Main2Activity implements Add_Task_Fragment.OnFragmentInteractionListener {
    private static final String TAG="FragmentActivity";
    private String description;
    private String time;

    Api_Functions api_functions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        api_functions = new Api_Functions(this);

        String screen = (String) getIntent().getExtras().getSerializable("screen");
        if(screen.equals("addTask")){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Add_Task_Fragment add_taskFragment = new Add_Task_Fragment();
            ft.replace(R.id.placeholder, add_taskFragment);
            ft.commit();
        }
        else if (screen.equals("task")){
            description = (String) getIntent().getExtras().getSerializable("description");
            time = (String) getIntent().getExtras().getSerializable("time");

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            TaskFragment taskFragment = TaskFragment.newInstance(description,time);
            ft.replace(R.id.placeholder, taskFragment);
            ft.commit();
        }


    }
    @Override
    public int getLayoutResource() {
        return R.layout.activity_fragment;
    }


    @Override
    public void addTask(String decription) {
        Log.d(TAG,"addTask testing");
        api_functions.postTask(description);
    }
}
