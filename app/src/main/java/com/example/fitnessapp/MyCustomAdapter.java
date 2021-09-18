package com.example.fitnessapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Exercise> list = new ArrayList<Exercise>();
    private Context context;
    private FitnessAppDB fdb;
    private String currUser;

    public MyCustomAdapter(ArrayList<Exercise> list, Context context, FitnessAppDB fdb, String currUser) {
        this.list = list;
        this.context = context;
        this.fdb = fdb;
        this.currUser = currUser;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.exercise_list_item, null);
        }
        Exercise currExercise = list.get(position);
        String title = currExercise.getTitle();
        String description = currExercise.getDescription();
        String equipment = currExercise.getEquipment();
        String category = currExercise.getCategory();

        String content = "Exercise: " + title + "\n";
        content += "Description: " + description + "\n";
        content += "Equipment Needed: " + equipment + "\n";
        content += "Category: " + category;

        //Handle TextView and display string
        TextView textViewResult = (TextView)view.findViewById(R.id.text_view_result);
        textViewResult.setText(content);

        //Handle buttons and add onClickListeners
        Button savebtn= (Button)view.findViewById(R.id.save_button);

        savebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User user = fdb.user().findUserByUsername(currUser);
                ArrayList<Exercise> currExercises = user.getExercises();
                Exercise newExercise = new Exercise(title,description, equipment,category);
                currExercises.add(newExercise);
                fdb.user().updateExercises(currExercises,currUser);
            }
        });
        return view;
    }
}
