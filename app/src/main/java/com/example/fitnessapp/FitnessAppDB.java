package com.example.fitnessapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class}, version = 6, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class FitnessAppDB extends RoomDatabase {
    private static FitnessAppDB sInstance;
    public abstract UserDao user();

    public static synchronized FitnessAppDB getInstance(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FitnessAppDB.class,
                    "fitnessapp.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    public void seed() { // prepopulating database with some users and books
        if (user().count() == 0) {
            User alex = new User("alex", "123");
            User ivan = new User("ivan", "123");
            User noah = new User("noah", "123");
            User jeremy = new User("jeremy", "123");
            long[] user_ids = user().insertUsers(alex, ivan, noah, jeremy);
        }
    }
}
