package com.example.fitnessapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
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

    @Query("DELETE FROM users WHERE username = :username")
    void deleteByUsername(String username);

//    @Query("SELECT exercises FROM users WHERE username = :username")
//    ArrayList<Exercise> getUserExercises(String username);

    @Query("UPDATE users SET mExercises = :exercises WHERE username = :username")
    void updateExercises(ArrayList<Exercise> exercises, String username);

    @Query("UPDATE users SET username = :username, password = :password WHERE username = :old_username")
    void updateUsernameAndPassword(String username, String password, String old_username);

    @Query("UPDATE users SET username = :username WHERE username = :old_username")
    void updateUsername(String username, String old_username);

    @Query("UPDATE users SET password = :password WHERE username = :old_username")
    void updatePassword(String password, String old_username);
}
