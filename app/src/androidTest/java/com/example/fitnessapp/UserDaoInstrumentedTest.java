package com.example.fitnessapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class UserDaoInstrumentedTest {

    FitnessAppDB db;

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
        db.seed();
        User found = db.user().findUserByUsername("alex");
        assert(found != null);
    }

    @Test
    public void getUserExercise() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);

        User u1 = new User("user1", "password");
        db.user().addUser(u1);

        User copyOfU1 = db.user().findUserByUsername("user1");
        ArrayList<Exercise> ex;
        ex = copyOfU1.getExercises();
        assert(ex != null);

        db.user().deleteByUsername("user1");
    }

    @Test
    public void addExerciseToUser() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);

        User u1 = new User("user1", "password");
        db.user().addUser(u1);

        Exercise exercise = new Exercise("planks", "get in plank position and hold", "mat", "core");
        User copyOfU1 = db.user().findUserByUsername("user1");
        ArrayList<Exercise> u1Exercises= copyOfU1.getExercises();
        u1Exercises.add(exercise);
        db.user().updateExercises(u1Exercises, "user1");

        assert(u1Exercises.size() == db.user().findUserByUsername("user1").getExercises().size());
    }
}
