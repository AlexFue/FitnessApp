package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private Button btn_create;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FitnessAppDB db;
        db = FitnessAppDB.getInstance(this);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_create = findViewById(R.id.btn_create);
        btn_back = findViewById(R.id.btn_back);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidUsername(et_username.getText().toString(), db) && isValidPassword(et_password.getText().toString())) {
                    User newUser = new User(et_username.getText().toString(), et_password.getText().toString());
                    db.user().addUser(newUser);
                    Toast.makeText(CreateAccount.this, "Successfully Created Account!", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CreateAccount.this.finish();
                            Intent i = new Intent(CreateAccount.this, MainActivity.class);
                            startActivity(i);
                        }
                    }, 2000);
                }
                else {
                    Toast.makeText(CreateAccount.this, "Error! Make username unique & fill out all fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static boolean isValidUsername(String username, FitnessAppDB db) {
        if(!username.equals("")) {
            User found = db.user().findUserByUsername(username);
            if(found == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        if(!password.equals("")){
            return true;
        }
        return false;
    }
}