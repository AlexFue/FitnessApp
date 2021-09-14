package com.example.fitnessapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    @ColumnInfo(name="username")
    private String mUsername;

    @ColumnInfo(name="password")
    private String mPassword;

    @ColumnInfo(name="exercises")
    private ArrayList<Exercise> mExercises;

    public User(String mUsername, String mPassword, ArrayList<Exercise> mExercises){
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mExercises = mExercises;
    }

    public User(String mUsername, String mPassword){
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mExercises = new ArrayList<Exercise>();
    }

    public int getUserId() {
        return mUserId;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String toSting(){
        return "username: " + mUsername + " password: " + mPassword;
    }

    public ArrayList<Exercise> getExercises() {
        return mExercises;
    }

    public void addExercise(Exercise ex) {
        mExercises.add(ex);
    }
}
