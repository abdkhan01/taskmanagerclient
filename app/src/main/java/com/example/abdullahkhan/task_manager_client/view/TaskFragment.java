package com.example.abdullahkhan.task_manager_client.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abdullahkhan.task_manager_client.R;

public class TaskFragment extends Fragment {

    private Button deleteButton;
    private TextView timeTextView;
    private EditText descriptionTextView;
    private String TAG;
    private String description;
    private String time;
    // Creates a new fragment given an int and title
    // DemoFragment.newInstance(5, "Hello");
    public static TaskFragment newInstance(String description, String time) {
        TaskFragment taskFragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString("description", description);
        args.putString("time", time);
        taskFragment.setArguments(args);
        return taskFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments

        description = getArguments().getString("description", "description");
        time = getArguments().getString("time", "time");
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.task_layout, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        deleteButton = (Button) view.findViewById(R.id.delete_button);
        timeTextView = (TextView) view.findViewById(R.id.time_textView);
        descriptionTextView = (EditText) view.findViewById(R.id.description_textView);

        timeTextView.setText(time);
        descriptionTextView.setText(description);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","Delete button pressed");
            }
        });

    }

}