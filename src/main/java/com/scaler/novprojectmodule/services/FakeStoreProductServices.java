package com.scaler.novprojectmodule.services;

import com.scaler.novprojectmodule.dto.FakeStoreProductDto;
import com.scaler.novprojectmodule.dto.ProductUpdateRequest;
import com.scaler.novprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.novprojectmodule.models.Category;
import com.scaler.novprojectmodule.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Service("fakeStoreProductService")
public class FakeStoreProductServices implements ProductService {

    // Inside this, fake store is going to be third party service

    private RestTemplate restTemplate; //  calling resttemplate in service
    private RedisTemplate redisTemplate;

    public FakeStoreProductServices(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }



    public Product getSingleProduct(Long id) throws ProductNotFoundException {
       System.out.println("Inside FK product service");

       Product redisProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+id);

        if (redisProduct != null) {

            return redisProduct;
        }


      FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
              "https://fakestoreapi.com/products/"+id,
               FakeStoreProductDto.class);
      //  System.out.println(fakeStoreProductDto.toString());

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found with id "+id);
        }

        redisTemplate.opsForHash().put("PRODUCTS","PRODUCTS_"+id,fakeStoreProductDto.getProduct());

        return fakeStoreProductDto.getProduct();

    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName, String searchQuery) {
        return null;
    }



    //    public Product deleteSingleProduct(Long id,
//                                       String title, String description,
//                                       Double price, String category, String imageurl){
//        System.out.println("Inside FK product service");
//        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
//            "https://fakestoreapi.com/products/",
//                FakeStoreProductDto.class);
//
//        assert fakeStoreProductDto != null;
//        return fakeStoreProductDto.getProduct();
//    }
    @Override
    public void updateProduct(Long id, String title, String description, Double price, String url) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(url);

        restTemplate.put("https://fakestoreapi.com/products/"+id,
                fakeStoreProductDto);
    }

    @Override
    public void deleteProduct(Long id) {
        System.out.println("Product "+id+" deleted");
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        Long productId = null;

        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }


    @Override
    public Category createCategory(String title) {
        return null;
    }

    @Override
    public List<Product> searchProducts(String query) {
        return List.of();
    }



    public List<Product> getAllProducts() {
        return List.of();
    }

    public Product createProduct(Long id, String title, String description, Double price, String category, String imageurl) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(imageurl);

        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);
        return response.getProduct();
    }




}
