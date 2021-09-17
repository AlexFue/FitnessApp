package com.example.fitnessapp;

import static com.example.fitnessapp.CreateAccount.isValidPassword;
import static com.example.fitnessapp.CreateAccount.isValidUsername;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CreateAccountInstrumentedTest {

    private FitnessAppDB db;

    @Test
    public void validAccountTest() {
        // Context of the app under test.
        User u1 = new User("test1", "password1");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);
        db.seed();

        boolean validUsername = isValidUsername(u1.getUsername(), db);
        assert(validUsername == true);

        boolean validPassword = isValidPassword(u1.getPassword());
        assert(validPassword == true);
    }

    @Test
    public void invalidAccountTest() {
        // Context of the app under test.
        User u1 = new User("alex", "");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);
        db.seed();

        boolean invalidUsername =  isValidUsername(u1.getUsername(), db);
        assert(invalidUsername == false);

        boolean invalidPassword = isValidPassword(u1.getPassword());
        assert(invalidPassword == false);
    }

    @Test
    public void emptyUsernameTest() {
        User u1 = new User("", "fsdfsd");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = FitnessAppDB.getInstance(appContext);
        db.seed();

        boolean invalidUsername =  isValidUsername(u1.getUsername(), db);
        assert(invalidUsername == false);

        boolean invalidPassword = isValidPassword(u1.getPassword());
        assert(invalidPassword == true);
    }
}
