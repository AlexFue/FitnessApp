/**
 * This class created an Exercise which has a name, description, equipment, and category.
 * Setters and getters implemented.
 * @author  Ivan MEndoza
 * @version 1.0
 * @since   2021-09-17
 */
package com.example.fitnessapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int mExerciseId;

    @ColumnInfo(name="title")
    private String mTitle;

    @ColumnInfo(name="description")
    private String mDescription;

    @ColumnInfo(name="equipment")
    private String mEquipment;

    @ColumnInfo(name="category")
    private String mCategory;

    public Exercise(String mTitle, String mDescription, String mEquipment, String mCategory){
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mEquipment = mEquipment;
        this.mCategory = mCategory;
    }

    public int getExerciseId() {
        return mExerciseId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEquipment() {
        return mEquipment;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setExerciseId(int mExerciseId) {
        this.mExerciseId = mExerciseId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setEquipment(String mEquipment) {
        this.mEquipment = mEquipment;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}