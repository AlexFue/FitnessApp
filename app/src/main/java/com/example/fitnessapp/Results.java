package com.example.fitnessapp;

import java.util.List;

public class Results {
    private String name;
    private String description;
    private Category category;
    private List<Equipment> equipment;

    public Category getCategory() {
        return category;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
