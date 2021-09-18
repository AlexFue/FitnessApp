package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountInfo extends AppCompatActivity {

    private Bundle bund;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        bund = this.getIntent().getExtras();

        String username = bund.getString("username");
        String password = bund.getString("password");

        TextView welcome = findViewById(R.id.user_welcome);
        TextView user_chng = findViewById(R.id.username_change);
        TextView pass_chng = findViewById(R.id.password_change);

        welcome.setText("Welcome " + username + "!");
        user_chng.setText(username);
        pass_chng.setText(password);
    }

    public void edit(View view){
        Intent i  = new Intent(this, EditAccount.class);
        i.putExtras(bund);
        startActivity(i);
    }

    public void logout(View view){
        Intent x = new Intent(this, LoginPage.class);
        startActivity(x);
        finish();
    }

}
