package com.hurtownia.database.products;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product product);
    @Update
    void update(Product product);
    @Delete
    void delete(Product product);
    @Query("SELECT * FROM Products ORDER BY name ASC")
    LiveData<List<Product>> getAllProducts();
}
