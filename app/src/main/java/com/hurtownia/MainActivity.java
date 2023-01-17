package com.hurtownia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.Users;
import com.hurtownia.database.user.ViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);
        Button loginButton = findViewById(R.id.loginButton);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        loginButton.setOnClickListener(view -> {
            String sLogin = name.getText().toString();
            String sPassword = password.getText().toString();
            int loginResult;

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
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private boolean login(String login, String password) {
        Users up = viewModel.find(login);
        if(up == null)  return false;
        return up.getLogin().equals(login) && up.getPassword().equals(password);
    }
}