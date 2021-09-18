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

/**
 * Abstract: Activity that displays list of equipment from equipment api endpoint
 * Contributors: Alex, Ivan
 */

public class EquipmentList extends AppCompatActivity {


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
                    Intent newInt = new Intent(EquipmentList.this, MainWorkoutPage.class);
                    newInt.putExtra("username", username);
                    startActivity(newInt);
                } else if (itemId == R.id.action_saved) {
                    Intent newInt = new Intent(EquipmentList.this, MyExercises.class);
                    newInt.putExtra("username", username);
                    startActivity(newInt);
                } else if (itemId == R.id.action_logout) {
                    Intent newInt = new Intent(EquipmentList.this, HomePage.class);
                    startActivity(newInt);
                }
                else if(itemId == R.id.action_comments) {
                    Intent newInt = new Intent(EquipmentList.this, CommentList.class);
                    newInt.putExtra("username", username);
                    startActivity(newInt);
                }
                else if(itemId == R.id.action_exercises) {
                    Intent newInt = new Intent(EquipmentList.this, EquipmentList.class);
                    newInt.putExtra("username", username);
                    startActivity(newInt);
                }
                finish();
                return true;
            }
        });
    }

    /**
     * Function Description: gets the api response from endpoint
     * @return Call<EquipmentResponse>
     */
    public static Call<EquipmentResponse> getRetrofitResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExercisesApi ExercisesAPI = retrofit.create(ExercisesApi.class);
        Call<EquipmentResponse> call = ExercisesAPI.getEquipment();
        return call;
    }

    /**
     * Function Description: extracts workout comments from api response and added them to page
     * @param call
     * @param tv_result
     * @return boolean
     */
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