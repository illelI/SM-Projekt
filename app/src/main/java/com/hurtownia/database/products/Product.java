package com.hurtownia.database.products;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "quantity")
    private int quantity;
    @NonNull
    @ColumnInfo(name = "image")
    private Image image;

    public Product(@NonNull String name, int quantity, @NonNull Image image) {
        this.name = name;
        this.quantity = quantity;
        this.image = image;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    @NonNull
    public Image getImage() {
        return image;
    }

    public void setImage(@NonNull Image image) {
        this.image = image;
    }
}
