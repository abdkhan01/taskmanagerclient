package com.example.abdullahkhan.task_manager_client.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.abdullahkhan.task_manager_client.view.FragmentActivity;
import com.example.abdullahkhan.task_manager_client.R;

import java.util.List;

import com.example.abdullahkhan.task_manager_client.Model.Task;

public class Adapter_Tasks_RecyclerView extends RecyclerView.Adapter <Adapter_Tasks_RecyclerView.ViewHolder> {

    private List<Task> values;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView description_tv;
        public CheckBox completed_cb;
        public TextView time_tv;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            description_tv = (TextView) v.findViewById(R.id.task_description_textview);
            completed_cb = (CheckBox) v.findViewById(R.id.task_completed_checkbox);
            time_tv = (TextView) v.findViewById(R.id.task_createdTime_textview);

            description_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),FragmentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("description",values.get(getAdapterPosition()).getDescription());
                    bundle.putSerializable("time",values.get(getAdapterPosition()).getCreatedAt());

                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

    public void add(int position, Task item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter_Tasks_RecyclerView(List<Task> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter_Tasks_RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.task_rv_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String description = values.get(position).getDescription();
        holder.description_tv.setText(description);

        String time = values.get(position).getCreatedAt();
        holder.time_tv.setText(time);

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }



}
