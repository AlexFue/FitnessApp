package com.example.fitnessapp;

import java.util.List;

public class ApiResponse {
    private int count;
    private List<Results> results;

    public int getCount() {
        return count;
    }

    public List<Results> getResults() {
        return results;
    }
}
