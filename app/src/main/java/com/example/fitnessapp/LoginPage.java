package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    private FitnessAppDB fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        fdb = FitnessAppDB.getInstance(this);
    }

    public void signIn(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());

        User userExist = fdb.user().findUserByUsername(user);

        String real_user = userExist.getUsername();
        String real_pass = userExist.getPassword();

        if(user.equals("") || pass.equals("")){
            String error_msg = "Fill in both fields.";
            Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
        }
        else if(real_user.equals(user) && real_pass.equals(pass)){
            Intent i = new Intent(this, MainWorkoutPage.class);
            startActivity(i);
        }
        else{
            String error_msg = "Incorrect Username or Password.";
            Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
        }
    }

    public static String isValidCredentials(String username, String password, FitnessAppDB db) {
        return "reue";
    }
}
