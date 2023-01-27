package com.hurtownia.recyclerview.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hurtownia.R;
import com.hurtownia.database.DataConverter;

public class ProductsViewHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView quantity;
    private ProductsViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.name);
        quantity = itemView.findViewById(R.id.quantity);
    }
    public void bind(String name, int quantity, byte[] image) {
        this.name.setText(name);
        this.quantity.setText(String.valueOf(quantity));
        this.image.setImageBitmap(DataConverter.convertToImage(image));
    }
    static ProductsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_view, parent, false);
        return new ProductsViewHolder(view);
    }
}
