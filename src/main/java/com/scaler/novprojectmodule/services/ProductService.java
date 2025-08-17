package com.scaler.novprojectmodule.services;

import com.scaler.novprojectmodule.dto.ProductUpdateRequest;
import com.scaler.novprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.novprojectmodule.models.Category;
import com.scaler.novprojectmodule.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName, String searchQuery);



    Product createProduct(Long id, String title, String description, Double price, String category, String imageurl);
    void updateProduct(Long id, String title, String description, Double price, String image );
    void deleteProduct(Long id);

    List<Product> getProductsByCategory(String category);

    List<Category> getAllCategories();

    Category createCategory(String title);
    List<Product> searchProducts(String query);


}
