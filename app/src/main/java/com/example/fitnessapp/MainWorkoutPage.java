package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWorkoutPage extends AppCompatActivity {
    private TextView textViewResult;
    private Bundle bun;
    private String currUser;
    private BottomNavigationView bottomNavigationView;
    private FitnessAppDB fdb;
    private ArrayList<Exercise> testArrEx = new ArrayList<>();
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_workout_page);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        textViewResult = findViewById(R.id.text_view_result);
        fdb = FitnessAppDB.getInstance(this);
        bun = getIntent().getExtras();
        currUser = bun.getString("username");
        User user = fdb.user().findUserByUsername(currUser);
        password = user.getPassword();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                    int itemId = item.getItemId();
                    if (itemId == R.id.action_home) {
                        Intent newInt1 = new Intent(MainWorkoutPage.this, MainWorkoutPage.class);
                        newInt1.putExtra("username", currUser);
                        startActivity(newInt1);
                    } else if (itemId == R.id.action_saved) {
                        Intent newInt2 = new Intent(MainWorkoutPage.this, MyExercises.class);
                        newInt2.putExtra("username", currUser);
                        startActivity(newInt2);
                    } else if (itemId == R.id.action_logout) {
                        Intent newInt3 = new Intent(MainWorkoutPage.this, AccountInfo.class);
                        newInt3.putExtra("username", currUser);
                        newInt3.putExtra("password", password);
                        startActivity(newInt3);
                    }
                    else if(itemId == R.id.action_exercises) {
                        Intent newInt4 = new Intent(MainWorkoutPage.this, TestEq.class);
                        newInt4.putExtra("username", currUser);
                        startActivity(newInt4);
                    }
                    else if(itemId == R.id.action_comments) {
                        Intent newInt5 = new Intent(MainWorkoutPage.this, CommentList.class);
                        newInt5.putExtra("username", currUser);
                        startActivity(newInt5);
                    }
                    finish();
                return true;
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExercisesApi ExercisesApi = retrofit.create(ExercisesApi.class);

        Call<ApiResponse> call = ExercisesApi.getExercises();

        call.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                String content = "\n";

                ApiResponse post = response.body();


                content += "\nExercise count: " + post.getCount() + "\n\n";

                List<Results> results = post.getResults();
                Exercise testWork = new Exercise(results.get(0).getName(), results.get(0).getDescription(), results.get(0).getEquipment().get(0).toString(), results.get(0).getCategory().toString());
                testArrEx.add(testWork);

                ArrayList<Exercise> allExercises = new ArrayList<>();
                for (Results result : results) {
                    String title = result.getName();
                    String description = html2text(result.getDescription());
                    String category = result.getCategory().getName();
                    content += "Exercise Name: " + title + "\n";
                    content += "Description: " + description + "\n";
                    content += "Category: " + category + "\n";
                    content += "Equipment Needed: ";
                    String equipmentStr = "";
                    List<Equipment> equipments = result.getEquipment();
                    for (Equipment equipment : equipments) {
                        equipmentStr += equipment.getName() + ", ";
                    }
                    if (equipmentStr.length() > 1) {
                        equipmentStr = equipmentStr.substring(0, equipmentStr.length() - 2);
                    }

                    content += equipmentStr + "\n\n";
                    Exercise newExercise = new Exercise(title,description, equipmentStr,category);
                    allExercises.add(newExercise);

                }

                ListView listview = findViewById(R.id.listview);
                listview.setAdapter(new MyCustomAdapter(allExercises, App.context, fdb, currUser) );

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}


