package com.example.fitnessapp;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CreateAccountInstrumentedTest {
    @Test
    public void validAccountTest() {
        // Context of the app under test.
        User u1 = new User("test1", "password1");
        boolean validUsername = isValidUsername(u1.getUsername());
        assert(validUsername == true);

        boolean validPassword = isValidPassword(u1.getPassword());
        assert(validPassword == true);
    }

    public void invalidAccountTest() {
        // Context of the app under test.
        User u1 = new User("alex", "password1");
        boolean invalidUsername = isValidUsername(u1.getUsername());
        assert(invalidUsername == false);

        boolean validPassword = isValidPassword(u1.getPassword());
        assert(validPassword == false);
    }
}
