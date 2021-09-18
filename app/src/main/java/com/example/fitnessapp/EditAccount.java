package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccount extends AppCompatActivity {

    private FitnessAppDB fdb;
    private EditText user_change;
    private EditText pass_change;
    private EditText retype_pass;
    private Bundle bund;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_info);

        fdb = FitnessAppDB.getInstance(this);
        user_change = findViewById(R.id.edit_user);
        pass_change = findViewById(R.id.edit_password);
        retype_pass = findViewById(R.id.retype_password);
        Button back = findViewById(R.id.return_button);

        bund = this.getIntent().getExtras();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void updateAccount(View v) {

        String old_user = bund.getString("username");
        String old_pass = bund.getString("password");

        String new_user = String.valueOf(user_change.getText());
        String new_pass = String.valueOf(pass_change.getText());
        String check_new_pass = String.valueOf(retype_pass.getText());
        boolean pass_empty = new_pass.equals("");
        boolean user_empty = new_user.equals("");

        User user_checker = fdb.user().findUserByUsername(new_user);

        if(user_empty && pass_empty){
            Toast.makeText(this, "Enter the data you would like to change.", Toast.LENGTH_SHORT).show();
        } else {
            if (user_empty) {
                if(!pass_empty && new_pass.equals(check_new_pass)){
                    fdb.user().updatePassword(new_pass, old_user);

                    Bundle new_bund = new Bundle();

                    new_bund.putString("username", old_user);
                    new_bund.putString("password", new_pass);

                    Intent i = new Intent(this, AccountInfo.class);
                    i.putExtras(new_bund);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(this, "Enter the data you would like to change.", Toast.LENGTH_SHORT).show();
                }
            } else if(pass_empty && !user_empty){
                if(user_checker == null) {
                    fdb.user().updateUsername(new_user, old_user);

                    Bundle new_bund = new Bundle();

                    new_bund.putString("username", new_user);
                    new_bund.putString("password", old_pass);

                    Intent i = new Intent(this, AccountInfo.class);
                    i.putExtras(new_bund);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(this, "Username Already Taken", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (new_pass.equals(check_new_pass) && !pass_empty) {
                    if (user_checker == null) {
                        fdb.user().updateUsernameAndPassword(new_user, new_pass, old_user);

                        Bundle new_bund = new Bundle();

                        new_bund.putString("username", new_user);
                        new_bund.putString("password", new_pass);

                        Intent i = new Intent(this, AccountInfo.class);
                        i.putExtras(new_bund);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(this, "Username Already Taken", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }


}
