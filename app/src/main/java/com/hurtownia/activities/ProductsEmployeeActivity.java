package com.hurtownia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hurtownia.R;
import com.hurtownia.database.user.Users;
import com.hurtownia.fragments.ProductsFragment;

public class ProductsEmployeeActivity extends AppCompatActivity {
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_employee);
        FloatingActionButton fab = findViewById(R.id.fab);
        user = (Users) getIntent().getExtras().get("USER");
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsEmployeeActivity.this, AddProductActivity.class);
            startActivity(intent);
        });
        displayFragment();
    }
    public void displayFragment() {
        ProductsFragment fragment = ProductsFragment.newInstance(user);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.products_fragment_containter, fragment).addToBackStack(null).commit();
    }
}