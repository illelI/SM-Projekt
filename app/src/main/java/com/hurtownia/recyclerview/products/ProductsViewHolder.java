package com.hurtownia.recyclerview.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hurtownia.Hurtownia;
import com.hurtownia.R;
import com.hurtownia.database.DataConverter;

public class ProductsViewHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView quantity;
    private final TextView price;
    private ProductsViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.name);
        quantity = itemView.findViewById(R.id.quantity);
        price = itemView.findViewById(R.id.tv_price);
    }
    public void bind(String name, int quantity, float price ,byte[] image) {
        String sPrice = Hurtownia.getContext().getResources().getString(R.string.price);
        this.name.setText(name);
        this.quantity.setText(String.valueOf(quantity));
        this.image.setImageBitmap(DataConverter.convertToImage(image));
        this.price.setText(sPrice + price);
    }
    static ProductsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_view, parent, false);
        return new ProductsViewHolder(view);
    }
}
