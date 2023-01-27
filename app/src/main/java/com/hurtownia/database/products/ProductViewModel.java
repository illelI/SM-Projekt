package com.hurtownia.database.products;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private final ProductRepository pRepository;
    private final LiveData<List<Product>> allProducts;

    public ProductViewModel(Application application) {
        super(application);
        pRepository = new ProductRepository(application);
        allProducts = pRepository.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public void insert(Product product) {
        pRepository.insert(product);
    }

}
