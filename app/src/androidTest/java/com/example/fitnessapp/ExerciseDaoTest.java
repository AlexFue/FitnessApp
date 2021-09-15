package com.example.fitnessapp;

import android.content.Context;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

public class ExerciseDaoTest {

    @Test
    public void testGetMethods() {
        Exercise e1 = new Exercise("Push ups", "doing pushups", "none", "triceps");
        assertEquals("Push ups", e1.getTitle());
        assertEquals("doing pushups", e1.getDescription());
        assertEquals("none", e1.getEquipment());
        assertEquals("triceps", e1.getCategory());
    }

    @Test
    public void testSetMethods() {
        Exercise e1 = new Exercise("Push ups", "doing pushups", "none", "triceps");
        e1.setTitle("crunches");
        e1.setDescription("get on your back and reach the sky with your arms to your back");
        e1.setEquipment("mat");
        e1.setCategory("core");
        assertEquals("crunches", e1.getTitle());
        assertEquals("get on your back and reach the sky with your arms to your back", e1.getDescription());
        assertEquals("mat", e1.getEquipment());
        assertEquals("core", e1.getCategory());
    }
}