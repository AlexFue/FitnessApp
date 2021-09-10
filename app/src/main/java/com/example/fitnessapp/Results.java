package com.example.fitnessapp;

import com.google.gson.annotations.SerializedName;

public class Results {
    private int id;
    private String name;
    @SerializedName("body")
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
