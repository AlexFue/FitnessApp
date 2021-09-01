package com.example.fitnessapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreateAccountUnitTest {

    // Tests to verify if a account exists in a database with Create Account function

    // Should pass, account doesn't exist
    @Test
    public void verifyAccountEqualsTrue() {
        String username = "alexdummy";
        String password = "123";
        // initialize db
        assertEquals(CreateAccount.verifyAccount(username, password), true);
    }

    // Should fail, account exists
    @Test
    public void verifyAccountEqualsFalse() {
        String username = "alex";
        String password = "123";
        // initialize db
        assertEquals(CreateAccount.verifyAccount(username, password), true);
    }
}
