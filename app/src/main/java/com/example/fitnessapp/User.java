package com.example.fitnessapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    @ColumnInfo(name="username")
    private String mUsername;

    @ColumnInfo(name="password")
    private String mPassword;

    public User(String mUsername, String mPassword){
        this.mUsername = mUsername;
        this.mPassword = mPassword;
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
}
