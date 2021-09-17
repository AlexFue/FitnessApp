package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MyExercises extends AppCompatActivity {
    private Bundle userNameBun;
    private String username;
    private FitnessAppDB fdb;
    private Exercise testEx;
    private Exercise testEx2;
    private Exercise testEx3;
    private TextView textViewMyWorkouts;
    private ArrayList<Exercise> workoutsSaved = new ArrayList<>();
    private ArrayList<Exercise> testArr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercises);
        textViewMyWorkouts = (TextView) findViewById(R.id.text_view_my_workouts);
        fdb = FitnessAppDB.getInstance(this);
        userNameBun = getIntent().getExtras();
        username = userNameBun.getString("username");
        User user = fdb.user().findUserByUsername(username);
        //Dummy Data For Now until Save for later functionality is added.
        testEx = new Exercise("Test", "test", "Test", "Test");
        testEx2 = new Exercise("Test2", "test2", "Test2", "Test2");
        testEx3 = new Exercise("Test3", "test3", "Test3", "Test3");

        testArr.add(testEx);
        testArr.add(testEx2);
        testArr.add(testEx3);

        user.setExercises(testArr);
        workoutsSaved = user.getExercises();
        String contentForScrollView = "";
        for(Exercise exercise : workoutsSaved){
            contentForScrollView += "\n\nExercise Name: " + exercise.getTitle()+ "\n";
            contentForScrollView += "Description: " + exercise.getDescription()+ "\n";
            contentForScrollView += "Category: " + exercise.getCategory()+ "\n";
            contentForScrollView += "Equipment Needed: " + exercise.getEquipment();
            contentForScrollView+="\n\n";
        }
        textViewMyWorkouts.append(contentForScrollView);
    }
}