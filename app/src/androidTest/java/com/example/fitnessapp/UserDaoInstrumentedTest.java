package com.example.fitnessapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserDaoInstrumentedTest {

    FitnessApp db;

    @Test
    public void test() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    // adds user to db and checks its therer
    @Test
    public void UserDaoTest() {

    }

    @Test
    public void UserCredentialsTest() {
        User u1 = new User("user1", "password1");
        assertEquals(u1.getUsername(), "user1");
        assertEquals(u1.getPassword(), "password");
    }

    @Test
    public void FindUsernameTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessApp.getInstance(appContext);
        User found = db.user().findUsername("alex");
        assert(found != null);
    }
}
