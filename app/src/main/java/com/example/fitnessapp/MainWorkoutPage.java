package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainWorkoutPage extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

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


                content += "\n\nExercise count: " + post.getCount() + "\n\n";

                List<Results> results = post.getResults();

                for (Results result : results) {
                    content += "Exercise ID: " + result.getId() + "\n";
                    content += "Exercise Name: " + result.getName() + "\n";
                    content += "Exercise Description" + result.getDescription() + "\n";
                }

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}


