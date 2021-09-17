package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        Bundle bund = this.getIntent().getExtras();

        String username = bund.getString("username");
        String password = bund.getString("password");

        TextView welcome = findViewById(R.id.user_welcome);
        TextView user_chng = findViewById(R.id.username_change);
        TextView pass_chng = findViewById(R.id.password_change);

        welcome.setText("Welcome " + username + "!");
        user_chng.setText(username);
        pass_chng.setText(password);

        Button back = findViewById(R.id.return_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void edit(View view){
        Intent i  = new Intent(this, EditAccount.class);
        startActivity(i);
    }


}
