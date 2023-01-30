package com.hurtownia.activities;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hurtownia.R;
import com.hurtownia.database.user.UserViewModel;
import com.hurtownia.database.user.Users;
import com.hurtownia.recyclerview.users.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchView searchView;
    private List<Users> usersList;
    private UserViewModel uvm;
    UsersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        uvm = new ViewModelProvider(this).get(UserViewModel.class);
        usersList = uvm.getAllUsers();
        adapter = new UsersAdapter(this, usersList);
        recyclerView = findViewById(R.id.rv_users);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
    private void filterList(String s) {
        List<Users> filteredList = new ArrayList<>();
        for(Users user : usersList) {
            if (user.getLogin().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(user);
            }
        }
        adapter.setUsersList(usersList);
    }
}