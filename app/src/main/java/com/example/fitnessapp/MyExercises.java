package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercises);
        textViewMyWorkouts = (TextView) findViewById(R.id.text_view_my_workouts);
        fdb = FitnessAppDB.getInstance(this);
        userNameBun = getIntent().getExtras();
        username = userNameBun.getString("username");
        User user = fdb.user().findUserByUsername(username);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        //Dummy Data For Now until Save for later functionality is added.
        testEx = new Exercise("Test", "test", "Test", "Test");
        testEx2 = new Exercise("Test2", "test2", "Test2", "Test2");
        testEx3 = new Exercise("Test3", "test3", "Test3", "Test3");

        testArr.add(testEx);
        testArr.add(testEx2);
        testArr.add(testEx3);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    Intent newInt1 = new Intent(MyExercises.this, MainWorkoutPage.class);
                    newInt1.putExtra("username", username);
                    startActivity(newInt1);
                } else if (itemId == R.id.action_saved) {
                    Intent newInt2 = new Intent(MyExercises.this, MyExercises.class);
                    newInt2.putExtra("username", username);
                    startActivity(newInt2);
                } else if (itemId == R.id.action_logout) {
                    Intent newInt3 = new Intent(MyExercises.this, HomePage.class);
                    startActivity(newInt3);
                }
                else if(itemId == R.id.action_exercises) {
                    Intent newInt4 = new Intent(MyExercises.this, TestEq.class);
                    newInt4.putExtra("username", username);
                    startActivity(newInt4);
                }
                else if(itemId == R.id.action_comments) {
                    Intent newInt5 = new Intent(MyExercises.this, CommentList.class);
                    newInt5.putExtra("username", username);
                    startActivity(newInt5);
                }
                finish();
                return true;
            }
        });
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