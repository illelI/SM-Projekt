package com.hurtownia.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hurtownia.R;
import com.hurtownia.database.user.Users;
import com.hurtownia.fragments.ProductsFragment;

public class ProductsActivity extends AppCompatActivity {
    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        user = (Users) getIntent().getExtras().get("USER");
        displayFragment();
    }
    public void displayFragment() {
        ProductsFragment fragment = ProductsFragment.newInstance(user);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.products_fragment_containter, fragment).addToBackStack(null).commit();
    }
}