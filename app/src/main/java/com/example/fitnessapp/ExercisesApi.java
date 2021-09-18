package com.example.fitnessapp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Abstract: get methods for endpoints
 * Contributors: Noah, Alex
 */

public interface ExercisesApi {

    @GET("exerciseinfo/?language=2")
    Call<ApiResponse> getExercises();

    @GET("equipment")
    Call<EquipmentResponse> getEquipment();

    @GET("exercisecomment/?language=2")
    Call<CommentResponse> getComment();


}
