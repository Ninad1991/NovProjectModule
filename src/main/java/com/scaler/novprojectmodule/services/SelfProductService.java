package com.scaler.novprojectmodule.services;

import com.scaler.novprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.novprojectmodule.models.Category;
import com.scaler.novprojectmodule.models.Product;

import com.scaler.novprojectmodule.repository.CategoryRepository;
import com.scaler.novprojectmodule.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()) {
            return p.get();
        }
            throw new ProductNotFoundException("Product is not found in our DB");
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Product createProduct(Long id, String title, String description,
                                 Double price, String categoryTitle, String imageurl) {

        Product p = new Product();

        Optional <Category> currentCat= categoryRepository.findByTitle(categoryTitle);

        if(currentCat.isEmpty()) {
            Category newCat = new Category();
            newCat.setTitle(categoryTitle);
            Category newRow = categoryRepository.save(newCat);
            p.setCategory(newRow);
        }
        else{
            Category currentCategory = currentCat.get();
            p.setCategory(currentCategory);
        }
        p.setTitle(title);
        p.setDescription(description);
        p.setImageurl(imageurl);
        p.setPrice(price);


        Product savedProduct=productRepository.save(p);
        return savedProduct;

    }

    @Override
    public void updateProduct(Long id, String title, String description, Double price, String category, String image) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product with ID " + id + " not found");
        }

        Product updateProduct  = productOptional.get();


    }


}
