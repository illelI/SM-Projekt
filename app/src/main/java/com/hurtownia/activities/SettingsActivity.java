package com.hurtownia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hurtownia.R;
import com.hurtownia.database.user.UserViewModel;
import com.hurtownia.database.user.Users;

public class SettingsActivity extends AppCompatActivity {
    private EditText newPass;
    private EditText newPassConfirm;
    private Users user;
    private UserViewModel uvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        newPass = findViewById(R.id.et_newPass);
        newPassConfirm = findViewById(R.id.et_newPassConfirm);
        Button changePass = findViewById(R.id.btn_changePass);
        Button deleteAcc = findViewById(R.id.btn_deleteAcc);
        user = (Users) getIntent().getExtras().get("USER");
        uvm = new ViewModelProvider(this).get(UserViewModel.class);
        changePass.setOnClickListener(view -> {
            String sPass;
            String sPassConfirm;
            sPass = newPass.getText().toString();
            sPassConfirm = newPassConfirm.getText().toString();
            if(sPass.isEmpty()) {
                Toast.makeText(this, R.string.empty_password, Toast.LENGTH_SHORT).show();
            } else if (!sPass.equals(sPassConfirm)) {
                Toast.makeText(this, R.string.password_dont_match, Toast.LENGTH_SHORT).show();
            } else {
                Snackbar snackbar = Snackbar.make(view, R.string.password_changed, Snackbar.LENGTH_SHORT);
                user.setPassword(sPass);
                uvm.update(user);
                snackbar.show();
            }
        });
        deleteAcc.setOnClickListener(view -> {
            uvm.delete(user);
            Snackbar snackbar = Snackbar.make(view, R.string.password_changed, Snackbar.LENGTH_SHORT);
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            snackbar.show();
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