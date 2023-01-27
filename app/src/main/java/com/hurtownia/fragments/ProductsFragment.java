package com.hurtownia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hurtownia.R;
import com.hurtownia.database.products.Product;
import com.hurtownia.database.products.ProductViewModel;
import com.hurtownia.database.user.Users;
import com.hurtownia.recyclerview.products.ProductsAdapter;

import java.util.List;

public class ProductsFragment extends Fragment {
    private ProductViewModel pvm;
    private Users user;
    public ProductsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =
                inflater.inflate(R.layout.fragment_products, container, false);
        pvm = new ViewModelProvider(this).get(ProductViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.rv_products);
        final ProductsAdapter adapter = new ProductsAdapter(new ProductsAdapter.ProductDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        pvm.getAllProducts().observe(getViewLifecycleOwner(), adapter::submitList);
        return view;
    }
}
