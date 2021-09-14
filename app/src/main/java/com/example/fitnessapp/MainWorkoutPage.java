package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;

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


                content += "\nExercise count: " + post.getCount() + "\n\n";

                List<Results> results = post.getResults();

                for (Results result : results) {
                    content += "Exercise Name: " + result.getName() + "\n";
                    content += "Description: " + html2text(result.getDescription()) + "\n";
                    content += "Category: " + result.getCategory().getName() + "\n";
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

                }

                textViewResult.append(content);
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


