package com.example.fitnessapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExercisesApi {

    @GET("exerciseinfo/?language=2")
    Call<ApiResponse> getExercises();

    @GET("equipment")
    Call<EquipmentResponse> getEquipment();
}
