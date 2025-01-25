package com.scaler.novprojectmodule.repository;

import com.scaler.novprojectmodule.models.Product;
import com.scaler.novprojectmodule.repository.projection.ProductProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    Product findByTitle(String title);
    Product findByDescription(String description);


    // Implement HQL
    @Query("select p FROM Product p where p.category.id=:catagoryID"  )
    List<Product> getProductByCategoryId(@Param("catagoryID") Long categoryid);

    // Implement Native Query

    @Query(value = "select * FROM Product p where p.category_id=:catagoryID", nativeQuery = true  )
    List<Product> getProductByCategoryIdNativeQuery(@Param("catagoryID") Long categoryid);


    // Implementing Projection Interface
    @Query(value = "select p.id as id,p.title as title FROM Product p where p.category_id=:catagoryID", nativeQuery = true  )
    List<ProductProjections> getProductByCategoryIdUsingProjections(@Param("catagoryID") Long categoryid);



    @Query(value = "select * FROM Product p where p.id=:ProductID", nativeQuery = true  )
    List<Product> getProductByProductIdNativeQuery(@Param("ProductID") Long productID);

    @Query("SELECT p.id AS id, p.title AS title, p.description AS description, p.price AS price, c.title AS categoryTitle FROM Product p JOIN p.category c")
    List<ProductProjections> findProductProjections();


}
