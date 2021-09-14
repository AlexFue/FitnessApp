package com.example.fitnessapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserDaoInstrumentedTest {

    FitnessAppDB db;

    @Test
    public void test() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    // adds user to db and checks its therer
    @Test
    public void UserDaoTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);

        User u1 = new User("user1", "password1");
        int before_add = db.user().count();
        db.user().addUser(u1);

        assert(db.user().count() > before_add);

        db.user().deleteByUsername("user1");
        assert(db.user().count() == before_add);

    }

    @Test
    public void UserCredentialsTest() {
        User u1 = new User("user1", "password1");
        assertEquals(u1.getUsername(), "user1");
        assertEquals(u1.getPassword(), "password1");
    }

    @Test
    public void FindUsernameTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);
        User found = db.user().findUserByUsername("alex");
        assert(found != null);
    }
}
