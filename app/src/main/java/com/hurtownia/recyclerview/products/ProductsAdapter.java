package com.hurtownia.recyclerview.products;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.hurtownia.database.products.Product;

import java.util.Arrays;

public class ProductsAdapter extends ListAdapter<Product, ProductsViewHolder> {
    public ProductsAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProductsViewHolder.create(parent);
    }
    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current.getName(), current.getQuantity(), current.getImage());
    }
    static public class ProductDiff extends DiffUtil.ItemCallback<Product> {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }
        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getQuantity() == newItem.getQuantity() && Arrays.equals(oldItem.getImage(), newItem.getImage());
        }
    }
}
