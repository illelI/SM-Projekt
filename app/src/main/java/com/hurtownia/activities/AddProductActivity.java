package com.hurtownia.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hurtownia.R;
import com.hurtownia.database.DataConverter;
import com.hurtownia.database.products.Product;
import com.hurtownia.database.products.ProductViewModel;
import com.hurtownia.databinding.ActivityAddProductBinding;

public class AddProductActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etQuantity;
    private EditText etPrice;
    private ProductViewModel pvm;
    private static final int CAMERA_INTENT = 5000;
    private static final int CAMERA_REQUEST_CODE = 6000;
    String name;
    int quantity;
    float price;
    Bitmap photo = null;
    private ActivityAddProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etName = findViewById(R.id.et_name);
        etQuantity = findViewById(R.id.et_quantity);
        etPrice = findViewById(R.id.et_price);
        Button btnPhoto = findViewById(R.id.btn_photo);
        Button add = findViewById(R.id.btn_add_product);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        pvm = new ViewModelProvider(this).get(ProductViewModel.class);
        btnPhoto.setOnClickListener(view -> takePhoto());
        add.setOnClickListener(view -> {
            name = etName.getText().toString();
            quantity = Integer.parseInt(etQuantity.getText().toString());
            price = Float.parseFloat(etPrice.getText().toString());
            if(photo == null || name.isEmpty() || price == 0.0f) {
                Toast.makeText(this, R.string.empty,Toast.LENGTH_SHORT).show();
            }
            else {
                Product product = new Product(name, quantity, price, DataConverter.convertToByteArray(photo));
                pvm.insert(product);
                startActivity(new Intent(AddProductActivity.this, ProductsActivity.class));
            }
        });
    }
    public void takePhoto() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, CAMERA_INTENT);

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_INTENT) {
            assert data != null;
            photo = (Bitmap) data.getExtras().get("data");
            binding.ivPhoto.setImageBitmap(photo);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}