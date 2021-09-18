package com.example.fitnessapp;

/**
 * Abstract: Java class to represent the results object in equipment endpoint
 * Contributors: Alex
 */

public class EquipmentResults {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
