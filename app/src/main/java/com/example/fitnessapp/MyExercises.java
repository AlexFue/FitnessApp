/**
 * This MyExercises activity looks for the user that is logged in and displays all the workouts the
 * user saved to his "save for later". The name, description, equipment, and category are all displayed
 * on activity_my_exercises layout file.
 * @author  Ivan MEndoza
 * @version 1.0
 * @since   2021-09-17
 */
package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MyExercises extends AppCompatActivity {
    private Bundle userNameBun;
    private String username;
    private FitnessAppDB fdb;
    private TextView textViewMyWorkouts;
    private ArrayList<Exercise> workoutsSaved = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
//    private Button account_info;
    private String passsword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercises);
        textViewMyWorkouts = (TextView) findViewById(R.id.text_view_my_workouts);
        fdb = FitnessAppDB.getInstance(this);
        userNameBun = getIntent().getExtras();
//        account_info = (Button) findViewById(R.id.account_info);
        username = userNameBun.getString("username");
        User user = fdb.user().findUserByUsername(username);
        passsword = user.getPassword();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                    Intent newInt3 = new Intent(MyExercises.this, AccountInfo.class);
                    newInt3.putExtra("username", username);
                    newInt3.putExtra("password", passsword);
                    startActivity(newInt3);
                } else if (itemId == R.id.action_exercises) {
                    Intent newInt4 = new Intent(MyExercises.this, TestEq.class);
                    newInt4.putExtra("username", username);
                    startActivity(newInt4);
                } else if (itemId == R.id.action_comments) {
                    Intent newInt5 = new Intent(MyExercises.this, CommentList.class);
                    newInt5.putExtra("username", username);
                    startActivity(newInt5);
                }
                finish();
                return true;
            }
        });
        workoutsSaved = user.getExercises();
        String contentForScrollView = "";
        for (Exercise exercise : workoutsSaved) {
            contentForScrollView += "\n\nExercise Name: " + exercise.getTitle() + "\n";
            contentForScrollView += "Description: " + exercise.getDescription() + "\n";
            contentForScrollView += "Category: " + exercise.getCategory() + "\n";
            contentForScrollView += "Equipment Needed: " + exercise.getEquipment();
            contentForScrollView += "\n\n";
        }
        textViewMyWorkouts.append(contentForScrollView);
    }
}