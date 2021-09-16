package com.example.fitnessapp;

import java.util.List;

public class EquipmentResponse {
    private int count;
    private String next;
    private String previous;
    private List<EquipmentResults> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<EquipmentResults> getResults() {
        return results;
    }
}
