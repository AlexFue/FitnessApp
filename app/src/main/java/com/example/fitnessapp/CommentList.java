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

public class CommentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        TextView tv_result;
        tv_result = findViewById(R.id.tv_results);
        tv_result.append("\n\n");

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
                    content += er.getComment() + "\n";
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