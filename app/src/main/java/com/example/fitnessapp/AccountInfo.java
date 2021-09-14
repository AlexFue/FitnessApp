package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        String username = bun.username;
        String password = bun.password;

        TextView welcome = findViewById(R.id.user_welcome);
        TextView user_chng = findViewById(R.id.username_change);
        TextView pass_chng = findViewById(R.id.password_change);

        welcome.setText("Welcome " + username + "!");
        user_chng.setText(username);
        pass_chng.setText(password);
    }

    public void edit(View view){
        Intent i  = new Intent(this, EditAccount.class);
        startActivity(i);
    }


}
