/**
 * This TestEq activity grabs all the equipment used in all workouts from the API https://wger.de/api/v2/
 * and displays it onto the activity_test_eq layout.
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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestEq extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private Bundle usernameBun;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_eq);

        TextView tv_result;
        tv_result = findViewById(R.id.tv_results);
        tv_result.append("\n\n");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        usernameBun = getIntent().getExtras();
        username = usernameBun.getString("username");
        Call<EquipmentResponse> call = getRetrofitResponse();
        returnResponse(call, tv_result);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    Intent newInt1 = new Intent(TestEq.this, MainWorkoutPage.class);
                    newInt1.putExtra("username", username);
                    startActivity(newInt1);
                } else if (itemId == R.id.action_saved) {
                    Intent newInt2 = new Intent(TestEq.this, MyExercises.class);
                    newInt2.putExtra("username", username);
                    startActivity(newInt2);
                } else if (itemId == R.id.action_logout) {
                    Intent newInt3 = new Intent(TestEq.this, HomePage.class);
                    startActivity(newInt3);
                }
                else if(itemId == R.id.action_exercises) {
                    Intent newInt4 = new Intent(TestEq.this, TestEq.class);
                    newInt4.putExtra("username", username);
                    startActivity(newInt4);
                }
                else if(itemId == R.id.action_comments) {
                    Intent newInt5 = new Intent(TestEq.this, CommentList.class);
                    newInt5.putExtra("username", username);
                    startActivity(newInt5);
                }
                finish();
                return true;
            }
        });
    }

    public static Call<EquipmentResponse> getRetrofitResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExercisesApi ExercisesAPI = retrofit.create(ExercisesApi.class);
        Call<EquipmentResponse> call = ExercisesAPI.getEquipment();
        return call;
    }

    public static boolean returnResponse(Call<EquipmentResponse> call, TextView tv_result) {
        call.enqueue(new Callback<EquipmentResponse>() {
            @Override
            public void onResponse(Call<EquipmentResponse> call, Response<EquipmentResponse> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code: " + response.code());
                    return;
                }
                EquipmentResponse equipment = response.body();
                List<EquipmentResults> results = equipment.getResults();
                for(EquipmentResults er : results) {
                    String content = "";
                    content += er.getName() + "\n\n";
                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<EquipmentResponse> call, Throwable t) {
                System.out.println("on failure failure");
                tv_result.setText(t.getMessage());
                return;
            }
        });
        return true;
    }
}