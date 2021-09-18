package com.example.fitnessapp;

/**
 * Abstract: Java class to represent the results object in comment endpoint
 * Contributors: Alex
 */

public class CommentResults {
    private int id;
    private int exercise;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExercise() {
        return exercise;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
