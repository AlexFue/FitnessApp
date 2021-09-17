package com.example.fitnessapp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExerciseDaoIntrumentedTest {

    @Test
    public void testGetMethods() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Exercise e1 = new Exercise("Push ups", "doing pushups", "none", "triceps");

        assertEquals("Push ups", e1.getTitle());
        assertEquals("doing pushups", e1.getDescription());
        assertEquals("none", e1.getEquipment());
        assertEquals("triceps", e1.getCategory());
    }

    @Test
    public void testSetMethods() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
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
