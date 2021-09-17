package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    private FitnessAppDB fdb;
    private Button btn_back;
    private EditText et_username;
    private EditText et_password;
    private Bundle bun = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        fdb = FitnessAppDB.getInstance(this);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        btn_back = findViewById(R.id.back_btn);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void signIn(View view){
        String username = String.valueOf(et_username.getText());
        String password = String.valueOf(et_password.getText());
        String result = isValidCredentials(username, password, fdb);

        clearFocus();
        switch (result) {
            case "valid":
                bun.putString("username", username);
                bun.putString("password", password);
                break;
            case "wrong user":
                isWrongUser();
                return;
            case "wrong password":
                isWrongPassword();
                return;
        }

        Intent intent = new Intent(LoginPage.this, AccountInfo.class);
        intent.putExtras(bun);
        startActivity(intent);
        finish();
    }

    public static String isValidCredentials(String username, String password, FitnessAppDB db) {
        if (username.isEmpty()) {
            return "wrong username";
        }
        if (password.isEmpty()) {
            return "wrong password";
        }

        User user = db.user().findUserByUsername(username);

        if (user == null) {
            return "wrong user";
        }

        if (user.getUsername().equals(username)) {
            if (user.getPassword().equals(password)) {
                return "valid";
            }
            else {
                return "wrong password";
            }
        }
        return "wrong user";
    }

    public void clearFocus() {
        et_password.clearFocus();
        et_username.clearFocus();
        et_username.setSelectAllOnFocus(false);
        et_password.setSelectAllOnFocus(false);
    }

    /**
     * Sets the focus and highlights password input text
     */
    public void isWrongPassword() {
        et_password.setSelectAllOnFocus(true);
        et_password.requestFocus();
        Toast.makeText(LoginPage.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets the focus and highlights username input text
     */
    public void isWrongUser() {
        et_username.setSelectAllOnFocus(true);
        et_username.requestFocus();
        Toast.makeText(LoginPage.this, "Incorrect Username!", Toast.LENGTH_SHORT).show();
    }
}
