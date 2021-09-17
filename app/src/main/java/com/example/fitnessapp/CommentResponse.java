package com.example.fitnessapp;

import java.util.List;

public class CommentResponse {
    private int count;
    private String next;
    private String previous;
    private List<CommentResults> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<CommentResults> getResults() {
        return results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setResults(List<CommentResults> results) {
        this.results = results;
    }
}
