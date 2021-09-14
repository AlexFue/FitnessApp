package com.example.fitnessapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExercisesApi {

    @GET("exerciseinfo")
    Call<ApiResponse> getExercises();
}