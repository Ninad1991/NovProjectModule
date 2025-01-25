package com.scaler.novprojectmodule.services;

import com.scaler.novprojectmodule.dto.FakeStoreProductDto;
import com.scaler.novprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.novprojectmodule.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServices implements ProductService {

    // Inside this, fake store is going to be third party service

    private RestTemplate restTemplate; //  calling resttemplate in service

    public FakeStoreProductServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public Product getSingleProduct(Long id) throws ProductNotFoundException {
       System.out.println("Inside FK product service");
      FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
              "https://fakestoreapi.com/products/"+id,
               FakeStoreProductDto.class);
      //  System.out.println(fakeStoreProductDto.toString());

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found with id "+id);
        }
        return fakeStoreProductDto.getProduct();

    }

    public Product deleteSingleProduct(Long id,
                                       String title, String description,
                                       Double price, String category, String imageurl){
        System.out.println("Inside FK product service");
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
            "https://fakestoreapi.com/products/",
                FakeStoreProductDto.class);

        assert fakeStoreProductDto != null;
        return fakeStoreProductDto.getProduct();
    }

    public void updateProduct(Long id, String title, String description, Double price, String category, String url) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(url);
        restTemplate.put("https://fakestoreapi.com/products/"+id,
                fakeStoreProductDto);
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
