package com.example.fitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_info);

        EditText user_change = findViewById(R.id.edit_user);
        EditText pass_change = findViewById(R.id.edit_password);
        EditText retype_pass = findViewById(R.id.retype_password);


    }

    public void updateAccount(View v){

    }


}
