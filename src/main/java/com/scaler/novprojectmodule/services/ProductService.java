package com.scaler.novprojectmodule.services;

import com.scaler.novprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.novprojectmodule.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(Long id, String title, String description, Double price, String category, String imageurl);

    void updateProduct(Long id, String title, String description, Double price, String category, String image );
}
