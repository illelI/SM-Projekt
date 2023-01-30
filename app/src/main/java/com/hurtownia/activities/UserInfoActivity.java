package com.hurtownia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hurtownia.R;
import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.UserViewModel;
import com.hurtownia.database.user.Users;
import com.hurtownia.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

    Users user;
    EditText login;
    EditText password;
    RadioGroup role;
    Button delete;
    Button update;
    RadioButton admin;
    RadioButton employee;
    RadioButton rUser;
    UserViewModel uvm;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        user = (Users) getIntent().getExtras().get("USER");
        ActivityUserInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        binding.setUser(user);
        login = findViewById(R.id.et_info_login);
        password = findViewById(R.id.et_info_password);
        role = findViewById(R.id.role);
        delete = (Button) findViewById(R.id.btn_info_delete);
        update = findViewById(R.id.btn_info_update);
        admin = findViewById(R.id.admin);
        employee = findViewById(R.id.employee);
        rUser = findViewById(R.id.userRadio);
        uvm = new ViewModelProvider(this).get(UserViewModel.class);
        admin.setOnClickListener(view -> admin.setChecked(true));
        employee.setOnClickListener(view -> employee.setChecked(true));
        rUser.setOnClickListener(view -> rUser.setChecked(true));
        switch (user.getRole()) {
            case Admin:
                binding.setVrole(R.id.admin);
                break;
            case Employee:
                binding.setVrole(R.id.employee);
                break;
            default:
                binding.setVrole(R.id.userRadio);
        }

    }
    public void delete(View v){
        uvm.delete(user);
        startActivity(new Intent(UserInfoActivity.this, UsersActivity.class));
    }
    public void update(View v) {
        Log.d("user", user.getLogin() + user.getPassword() + user.getRole());
        user.setLogin(login.getText().toString());
        user.setPassword(password.getText().toString());
        if(admin.isChecked())
            user.setRole(Roles.Admin);
        else if(employee.isChecked())
            user.setRole(Roles.Employee);
        else
            user.setRole(Roles.User);
        Log.d("user", user.getLogin() + user.getPassword() + user.getRole());
        uvm.update(user);
        startActivity(new Intent(UserInfoActivity.this, UsersActivity.class));
    }

}