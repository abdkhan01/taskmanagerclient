package com.example.abdullahkhan.task_manager_client.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.abdullahkhan.task_manager_client.controller.Adapter_Tasks_RecyclerView;
import com.example.abdullahkhan.task_manager_client.controller.Api_Functions;
import com.example.abdullahkhan.task_manager_client.model.Task;
import com.example.abdullahkhan.task_manager_client.R;

public class MainActivity extends Main2Activity {

    private static final String TAG = "MainActivity" ;
    public static boolean auth = false;
    private TextView textView_lastWeek;
    private TextView textView_today;
    private TextView textView_yesterday;

    private RecyclerView recyclerView_yesterday;
    private RecyclerView recyclerView_lastWeek;
    private RecyclerView recyclerView_today;

    private RecyclerView.Adapter mAdapter_lastWeek;

    private RecyclerView.LayoutManager layoutManager_yesterday;
    private RecyclerView.LayoutManager layoutManager_today;
    private RecyclerView.LayoutManager layoutManager_lastWeek;

    private ArrayList<Task> myDataset;



    private Api_Functions api_functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Log.d(TAG,"checking on create ManAcitvity");

        View include_lastWeek = (View) findViewById(R.id.include_lastweek);
        textView_lastWeek = (TextView) include_lastWeek.findViewById(R.id.include_textview);
        recyclerView_lastWeek = (RecyclerView) include_lastWeek.findViewById(R.id.include_recyclerView);

        View include_yesterday = (View) findViewById(R.id.include_yesterday);
        textView_yesterday = (TextView) include_yesterday.findViewById(R.id.include_textview);
        recyclerView_yesterday = (RecyclerView) include_yesterday.findViewById(R.id.include_recyclerView);

        View include_today = (View) findViewById(R.id.include_today);
        textView_today = (TextView) include_today.findViewById(R.id.include_textview);
        recyclerView_today = (RecyclerView) include_today.findViewById(R.id.include_recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        myDataset = new ArrayList<>();

        String status = sharedPref.getString("status","null");
        api_functions = new Api_Functions(this);

        if(status.equals("loggedin")){

            api_functions.getTasks(sharedPref);
        }
        else if(status.equals("null")){
           Log.d(TAG, "default value null" );
           Intent intent = new Intent(this,LoginActivity.class);
           startActivity(intent);
           Log.d(TAG,"testing testing asynch");
        }


//        createRecyclerViews();
    }
    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    public void createRecyclerViews(ArrayList<Task> tasks){
        myDataset = tasks;
        mAdapter_lastWeek = new Adapter_Tasks_RecyclerView(myDataset);

//--------------LAST WEEK--------------------

        textView_lastWeek.setText(R.string.lastweek);
        recyclerView_lastWeek.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_lastWeek = new LinearLayoutManager(this);
        recyclerView_lastWeek.setLayoutManager(layoutManager_lastWeek);

        // specify an adapter (see also next example)

        recyclerView_lastWeek.setAdapter(mAdapter_lastWeek);

//--------------YESTERDAY--------------------

        textView_yesterday.setText(R.string.yesterday);
        recyclerView_yesterday.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_yesterday = new LinearLayoutManager(this);
        recyclerView_yesterday.setLayoutManager(layoutManager_yesterday);

        // specify an adapter (see also next example)
        recyclerView_yesterday.setAdapter(mAdapter_lastWeek);

//--------------TODAY--------------------

        textView_today.setText(R.string.today);
        recyclerView_today.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_today = new LinearLayoutManager(this);
        recyclerView_today.setLayoutManager(layoutManager_today);

        // specify an adapter (see also next example)
        recyclerView_today.setAdapter(mAdapter_lastWeek);

        mAdapter_lastWeek.notifyDataSetChanged();
    }



    public void createDataSet(ArrayList<Task> tasks) {
        myDataset = tasks;

    }

    public void setMyDataset(ArrayList<Task> myDataset) {
        this.myDataset = myDataset;
    }

    public ArrayList<Task> getMyDataset() {
        return myDataset;
    }

    public void addTask(){


        Bundle bundle = new Bundle();
        bundle.putSerializable("screen","addTask");

        Intent intent = new Intent(this, com.example.abdullahkhan.task_manager_client.view.FragmentActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
