package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EquipmentList extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);

        TextView tv_result;
        tv_result = findViewById(R.id.tv_results);
        tv_result.append("\n\n");

        Call<EquipmentResponse> call = getRetrofitResponse();
        returnResponse(call, tv_result);
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
                    content += er.getName() + "\n";
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