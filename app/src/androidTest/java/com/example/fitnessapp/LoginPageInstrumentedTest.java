package com.example.fitnessapp;

import static com.example.fitnessapp.LoginPage.isValidCredentials;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

/**
 * Abstract: Tests if username/password is invalid and if both credentials are valid.
 * Contributors: Alex
 */

@RunWith(AndroidJUnit4.class)
public class LoginPageInstrumentedTest {

    private FitnessAppDB db;

    @Test
    public void testUsernameInvalidPassword(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String username = "alex";
        String password = "";
        db = FitnessAppDB.getInstance(appContext);
        db.seed();

        assertEquals("wrong password", isValidCredentials(username, password, db));
    }

    @Test
    public void testPasswordInvalidUsername(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String username = "alexe";
        String password = "123";
        db = FitnessAppDB.getInstance(appContext);
        db.seed();

        assertEquals( "wrong user", isValidCredentials(username, password, db));
    }

    @Test
    public void testValidLogin(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String username = "alex";
        String password = "123";
        db = FitnessAppDB.getInstance(appContext);
        db.seed();

        assertEquals("valid", isValidCredentials(username, password, db));
    }
}
