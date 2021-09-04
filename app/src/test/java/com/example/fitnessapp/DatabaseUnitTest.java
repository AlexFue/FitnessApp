package com.example.fitnessapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseUnitTest {

    public FitnessAppDB db;
    public FitnessAppDB fitnessAppDB;
    public List<User> users;

    // initialize database, seed it, check if default users are there
    @Test
    public void initDatabaseWithUsersTest() {
        db = FitnessAppDB.getInstance(this);
        db.seed();
        users = db.user().getAllUsers();

        assert(users.size() > 0);
    }
}
