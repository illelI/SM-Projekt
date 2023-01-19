package com.hurtownia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.Users;
import com.hurtownia.database.user.ViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private EditText passwordConfirmation;

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextView toLoginActivity = findViewById(R.id.tvLogin);
        Button register = findViewById(R.id.registerButton);
        login = findViewById(R.id.etLogin);
        password = findViewById(R.id.etRegisterPassword);
        passwordConfirmation = findViewById(R.id.etConfirmPassword);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        toLoginActivity.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            String sLogin = login.getText().toString();
            String sPassword = password.getText().toString();
            String sConfirm = passwordConfirmation.getText().toString();
            int registerResult;
            if(sLogin.isEmpty() || sPassword.isEmpty()) {
                registerResult = R.string.empty_login_or_password;
            }
            else if(!sPassword.equals(sConfirm)) {
                registerResult = R.string.password_dont_match;
            }
            else {
                registerResult = signUp(sLogin, sPassword);
            }

            Toast.makeText(RegistrationActivity.this, registerResult, Toast.LENGTH_SHORT).show();
            if(registerResult == R.string.registration_successful) {
                Users user = viewModel.find(sLogin);
                Intent intent;
                if(user.getRole() == Roles.Admin)
                    intent = new Intent(RegistrationActivity.this, AdminHomeActivity.class);
                else
                    intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }

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

    private int signUp(String login, String password) {
        Users user = viewModel.find(login);
        if(user != null) return R.string.already_exists;
        try {
            viewModel.insert(new Users(login, password, Roles.User));
        } catch (Exception e) {
            return R.string.registration_failed;
        }
        return R.string.registration_successful;
    }
}