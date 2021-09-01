package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    EditText et_username;
    EditText et_password;
    Button btn_create;
    Button btn_back;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_create = findViewById(R.id.btn_create);
        btn_back = findViewById(R.id.btn_back);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyAccount(username, password)) {
                    createAccount(username, password);
                    // send them to inside app to landing page
                }
                else {

                }
            }
        });
    }

    //checks if username exists in database and either one is not empty
    public static boolean verifyAccount(String username, String password) {

    }

    public boolean checkIfEmpty(String username, String password) {
        return username.equals("") || password.equals("");
    }

    //creates account in database once verified
    public void createAccount(String username, String password) {

    }
}