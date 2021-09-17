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

public class CommentList extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Bundle usernameBun;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        TextView tv_result;
        tv_result = findViewById(R.id.tv_results);
        tv_result.append("\n\n");
        usernameBun = getIntent().getExtras();
        username = usernameBun.getString("username");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    Intent newInt1 = new Intent(CommentList.this, MainWorkoutPage.class);
                    newInt1.putExtra("username", username);
                    startActivity(newInt1);
                } else if (itemId == R.id.action_saved) {
                    Intent newInt2 = new Intent(CommentList.this, MyExercises.class);
                    newInt2.putExtra("username", username);
                    startActivity(newInt2);
                } else if (itemId == R.id.action_logout) {
                    Intent newInt3 = new Intent(CommentList.this, HomePage.class);
                    startActivity(newInt3);
                }
                else if(itemId == R.id.action_exercises) {
                    Intent newInt4 = new Intent(CommentList.this, TestEq.class);
                    newInt4.putExtra("username", username);
                    startActivity(newInt4);
                }
                else if(itemId == R.id.action_comments) {
                    Intent newInt5 = new Intent(CommentList.this, CommentList.class);
                    newInt5.putExtra("username", username);
                    startActivity(newInt5);
                }
                finish();
                return true;
            }
        });
        Call<CommentResponse> call = getRetrofitResponse();
        returnResponse(call, tv_result);
    }

    public static Call<CommentResponse> getRetrofitResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wger.de/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExercisesApi ExercisesAPI = retrofit.create(ExercisesApi.class);
        Call<CommentResponse> call = ExercisesAPI.getComment();
        return call;
    }

    public static boolean returnResponse(Call<CommentResponse> call, TextView tv_result) {
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code: " + response.code());
                    return;
                }
                CommentResponse comment = response.body();
                List<CommentResults> results = comment.getResults();
                for(CommentResults er : results) {
                    String content = "";
                    content += er.getComment() + "\n\n";
                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                System.out.println("on failure failure");
                tv_result.setText(t.getMessage());
                return;
            }
        });
        return true;
    }

}