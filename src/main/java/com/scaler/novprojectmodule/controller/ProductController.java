package com.scaler.novprojectmodule.controller;

import com.scaler.novprojectmodule.dto.ErrorDTO;
import com.scaler.novprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.novprojectmodule.models.Product;
import com.scaler.novprojectmodule.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.LongFunction;


@RestController // it tells that the class is API class
public class ProductController {
    // CRUD apis around product
    // For the product
    // 1. to create a product
    // 2. get a product
    // 3. update a product
    // 4. delete a product

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) { // This is Depandancy Injection
        this.productService = productService;
    }
    // This will help in creating the product Depandance Injection

//    @RequestMapping(value = "/product", method= RequestMethod.GET) // this is long cut
    @PostMapping("/products") // it tells that the function will be used in API
    public Product createProduct(@RequestBody Product product) {
        Product p =  productService.createProduct(product.getId(),
                product.getTitle(),product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),product.getImageurl());
        return p;
//        return null;
    }
    // this will help get product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("Starting the API here");
        Product p = productService.getSingleProduct(id);
        System.out.println("Ending the API here");
        ResponseEntity<Product> response = new ResponseEntity<>(p, HttpStatus.OK);
        return response;
        //return null;
    }
    // this will help updating product
    public void updateProduct() {

    }
    // this will help delete product
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product del = productService.getSingleProduct(id);
        return del;
       // return null;
    }

    @PutMapping("products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id,
                product.getTitle(), product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),
                product.getImageurl());
        ResponseEntity<String> response = new ResponseEntity<>("Product updated successfully", HttpStatus.ACCEPTED);
        return response;
     //   return null;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFound(Exception e) {
    ErrorDTO errorDto = new ErrorDTO();
    errorDto.setMessage(e.getMessage());

    ResponseEntity<ErrorDTO> response = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

    return response;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;
    }

}
