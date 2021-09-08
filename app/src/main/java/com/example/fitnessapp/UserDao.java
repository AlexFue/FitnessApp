package com.example.fitnessapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    // returns number of rows in users table
    @Query("select count(*) from users")
    int count();

    @Query("select * from users")
    List<User> getAllUsers();

    @Query("select * from users where username = :username")
    User findUserByUsername(String username);

    @Query("select * from users where mUserId = :userId")
    User findById(int userId);

    @Insert
    long addUser(User user);

    @Insert
    long[] insertUsers(User... users);
}
