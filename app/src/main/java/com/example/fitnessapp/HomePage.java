package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Abstract: Activity that displays login/create account button for user to choose
 * Contributor: Alex
 */

public class HomePage extends AppCompatActivity {

    private Button btn_login;
    private Button btn_create;
    private FitnessAppDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        db = FitnessAppDB.getInstance(this);
        db.seed();

        btn_login = findViewById(R.id.btn_login);
        btn_create = findViewById(R.id.btn_create);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, LoginPage.class);
                startActivity(i);
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CreateAccount.class);
                startActivity(i);
            }
        });
    }
}