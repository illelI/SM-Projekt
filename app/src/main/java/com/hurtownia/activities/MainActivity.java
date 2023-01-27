package com.hurtownia.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hurtownia.R;
import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.Users;
import com.hurtownia.database.user.UserViewModel;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    private EditText name;
    private EditText password;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);
        TextView register = findViewById(R.id.tvRegister);
        Button loginButton = findViewById(R.id.loginButton);
        ImageView facebook = findViewById(R.id.iv_fb);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        facebook.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://www.facebook.com/people/JabPol-Hurtownia-Jaboli-Nowe-Miasto/100089794506429/");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {
            String sLogin = name.getText().toString();
            String sPassword = password.getText().toString();
            int loginResult;

            if (sLogin.isEmpty() || sPassword.isEmpty()) {
                loginResult = R.string.empty_login_or_password;
            }
            else {
                Users user = userViewModel.find(sLogin);
                if(user != null && user.getLogin().equals(sLogin) && user.getPassword().equals(sPassword)) {
                    loginResult = R.string.login_success;
                    Intent intent;
                    if(user.getRole() == Roles.Admin)
                        intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                    else
                        intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                }
                else {
                    loginResult = R.string.login_failed;
                }
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
}