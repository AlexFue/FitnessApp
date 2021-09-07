package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    public void signIn(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());


        if(user.equals("") || pass.equals("")){
            String error_msg = "Fill in both fields.";
            Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
        }
//        else if(user.equals(name_creds) && pass.equals(pass_creds)){ // this needs to be checked with the credentials take from the database
//            Intent i = new Intent(this, MainWorkoutPage.class); // this loads the main workout page with all the list of workouts
//            startActivity(i);
//        }
        else{
            String error_msg = "Incorrect Username or Password.";
            Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
        }
    }
}
