package com.hurtownia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sLogin = name.getText().toString();
                String sPassword = password.getText().toString();
                int loginResult = 0;

                if (sLogin.isEmpty() || sPassword.isEmpty()) {
                    loginResult = R.string.empty_login_or_password;
                }
                else if(login(sLogin, sPassword)) {
                    loginResult = R.string.login_success;
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    loginResult = R.string.login_failed;
                }

                Toast.makeText(MainActivity.this, loginResult, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean login(String login, String password) {
        return true;
    }
}