package com.hurtownia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.hurtownia.R;
import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.Users;
import com.hurtownia.databinding.ActivityAdminHomeBinding;
import com.hurtownia.databinding.ActivityHomeBinding;

public class AdminHomeActivity extends AppCompatActivity {

    private Users user;
    LinearLayout logout;
    LinearLayout products;
    LinearLayout settings;
    LinearLayout location;
    LinearLayout users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        user = (Users) getIntent().getExtras().get("USER");
        ActivityAdminHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home);
        binding.setUser(user);
        logout = findViewById(R.id.ll_logout);
        products = findViewById(R.id.ll_products);
        settings = findViewById(R.id.ll_settings);
        location = findViewById(R.id.ll_location);
        users = findViewById(R.id.ll_users);

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
            user = null;
            startActivity(intent);
        });


        products.setOnClickListener(view -> {
            Intent intent;
            if(user.getRole() == Roles.User)
                intent = new Intent(AdminHomeActivity.this, ProductsActivity.class);
            else
                intent = new Intent(AdminHomeActivity.this, ProductsEmployeeActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomeActivity.this, SettingsActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        });

        location.setOnClickListener(view -> startActivity(new Intent(AdminHomeActivity.this, LocationActivity.class)));
        users.setOnClickListener(view -> startActivity(new Intent(AdminHomeActivity.this, UsersActivity.class)));
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