package com.example.fitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button back = findViewById(R.id.return_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void updateAccount(View v){


    }


}
