package com.example.abdullahkhan.task_manager_client.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.abdullahkhan.task_manager_client.controller.Adapter_Tasks_RecyclerView;
import com.example.abdullahkhan.task_manager_client.model.Task;
import com.example.abdullahkhan.task_manager_client.R;

public class MainActivity extends AppCompatActivity {

    private TextView textView_lastWeek;
    private TextView textView_today;
    private TextView textView_yesterday;

    private RecyclerView recyclerView_yesterday;
    private RecyclerView recyclerView_lastWeek;
    private RecyclerView recyclerView_today;

    private RecyclerView.Adapter mAdapter_yesterday;
    private RecyclerView.Adapter mAdapter_lastWeek;
    private RecyclerView.Adapter mAdapter_today;

    private RecyclerView.LayoutManager layoutManager_yesterday;
    private RecyclerView.LayoutManager layoutManager_today;
    private RecyclerView.LayoutManager layoutManager_lastWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRecyclerViews();
    }

    public void createRecyclerViews(){

        ArrayList<Task> myDataset = createDataSet();
        mAdapter_lastWeek = new Adapter_Tasks_RecyclerView(myDataset);

//--------------LAST WEEK--------------------
        View include_lastWeek = (View) findViewById(R.id.include_lastweek);
        textView_lastWeek = (TextView) include_lastWeek.findViewById(R.id.include_textview);
        recyclerView_lastWeek = (RecyclerView) include_lastWeek.findViewById(R.id.include_recyclerView);

        textView_lastWeek.setText(R.string.lastweek);
        recyclerView_lastWeek.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_lastWeek = new LinearLayoutManager(this);
        recyclerView_lastWeek.setLayoutManager(layoutManager_lastWeek);

        // specify an adapter (see also next example)
//        ArrayList<Task> myDataset = createDataSet();
//        mAdapter_lastWeek = new Adapter_Tasks_RecyclerView(myDataset);
        recyclerView_lastWeek.setAdapter(mAdapter_lastWeek);

//--------------YESTERDAY--------------------

        View include_yesterday = (View) findViewById(R.id.include_yesterday);
        textView_yesterday = (TextView) include_yesterday.findViewById(R.id.include_textview);
        recyclerView_yesterday = (RecyclerView) include_yesterday.findViewById(R.id.include_recyclerView);

        textView_yesterday.setText(R.string.yesterday);
        recyclerView_yesterday.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_yesterday = new LinearLayoutManager(this);
        recyclerView_yesterday.setLayoutManager(layoutManager_yesterday);

        // specify an adapter (see also next example)
//        mAdapter_yesterday = new Adapter_Tasks_RecyclerView(myDataset);
        recyclerView_yesterday.setAdapter(mAdapter_lastWeek);

//--------------TODAY--------------------

        View include_today = (View) findViewById(R.id.include_today);
        textView_today = (TextView) include_today.findViewById(R.id.include_textview);
        recyclerView_today = (RecyclerView) include_today.findViewById(R.id.include_recyclerView);

        textView_today.setText(R.string.today);
        recyclerView_today.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_today = new LinearLayoutManager(this);
        recyclerView_today.setLayoutManager(layoutManager_today);

        // specify an adapter (see also next example)
//        mAdapter_today = new Adapter_Tasks_RecyclerView(myDataset);
        recyclerView_today.setAdapter(mAdapter_lastWeek);
        mAdapter_lastWeek.notifyDataSetChanged();
    }



    ArrayList<Task> createDataSet() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task t = new Task("This is test desc",false,"12:30 pm", "12:30 pm");
        Task t1 = new Task("This is test desc1",false,"12:31 pm", "12:30 pm");
        Task t2 = new Task("This is test desc2",false,"12:32 pm", "12:30 pm");
        Task t3 = new Task("This is test desc3",false,"12:30 pm", "12:30 pm");
        Task t4 = new Task("This is test desc4",false,"12:31 pm", "12:30 pm");
        Task t5 = new Task("This is test desc5",false,"12:32 pm", "12:30 pm");
        Task t6= new Task("This is test desc6",false,"12:30 pm", "12:30 pm");
        Task t7 = new Task("This is test desc7",false,"12:31 pm", "12:30 pm");
        Task t8 = new Task("This is test desc8",false,"12:32 pm", "12:30 pm");


        tasks.add(t);
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        tasks.add(t4);
        tasks.add(t5);
        tasks.add(t6);
        tasks.add(t7);
        tasks.add(t8);

        return tasks;

    }
}
