package com.hurtownia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hurtownia.R;
import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.Users;
import com.hurtownia.databinding.ActivityHomeBinding;

import java.io.Serializable;

public class HomeActivity extends AppCompatActivity implements Serializable {

    private Users user;
    LinearLayout logout;
    LinearLayout products;
    LinearLayout settings;
    LinearLayout location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user = (Users) getIntent().getExtras().get("USER");
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setUser(user);
        logout = findViewById(R.id.ll_logout);
        products = findViewById(R.id.ll_products);
        settings = findViewById(R.id.ll_settings);
        location = findViewById(R.id.ll_location);

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            user = null;
            startActivity(intent);
        });


        products.setOnClickListener(view -> {
            Intent intent;
            if(user.getRole() == Roles.User)
                intent = new Intent(HomeActivity.this, ProductsActivity.class);
            else
                intent = new Intent(HomeActivity.this, ProductsEmployeeActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
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