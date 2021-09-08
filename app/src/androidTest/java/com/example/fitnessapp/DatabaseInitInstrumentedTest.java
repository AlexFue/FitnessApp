package com.example.fitnessapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseInitInstrumentedTest {

    public FitnessAppDB db;
    public List<User> users;

    @Test
    public void initDatabaseTest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);
        db.seed();
        users = db.user().getAllUsers();
        assert(users.size() > 0);
    }
}
