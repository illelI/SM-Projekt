package com.hurtownia.database.products;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hurtownia.database.DB;

import java.util.List;

public class ProductRepository {
    private final ProductDao pDao;
    private final LiveData<List<Product>> allProducts;

    ProductRepository(Application application) {
        DB db = DB.getDatabase(application);
        pDao = db.pDao();
        allProducts = pDao.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    void insert(Product product) {
        DB.dbWriteExecutor.execute(() -> pDao.insert(product));
    }
}
