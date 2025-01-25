package com.scaler.novprojectmodule;

import com.scaler.novprojectmodule.models.Category;
import com.scaler.novprojectmodule.models.Product;
import com.scaler.novprojectmodule.repository.CategoryRepository;
import com.scaler.novprojectmodule.repository.ProductRepository;
import com.scaler.novprojectmodule.repository.projection.ProductProjections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NovProjectModuleApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testQueries() {
/*		List<Product> allProducts = productRepository.getProductByCategoryIdNativeQuery(2L);

		for (Product product : allProducts) {
			System.out.println(product);
		}*/

/*
		List<ProductProjections> productProjectionsList = productRepository.getProductByCategoryIdUsingProjections(2L);
		System.out.println(productProjectionsList.get(1).getId());
		System.out.println(productProjectionsList.get(1).getTitle());
*/
/*
		List<Product> productById = productRepository.getProductByProductIdNativeQuery(1L);
		System.out.println(productById);
*/


/*	List<Product> products = productRepository.findAll();
		System.out.println(products);*/

//		Product updateProduct = new Product();
	}

	@Test
	void fetchTypeTest() {
/*			Category cat = categoryRepository.findById(52L).get();
			System.out.println(cat.getId());
			System.out.println("We are done here");

			List<Product> currentProduct = cat.getProducts();
			System.out.println(currentProduct.size());
			System.out.println("We got the category as well");*/

		Category cat = categoryRepository.findAll().get(3);

		List<Product> products = cat.getProducts();

		for (Product product : products) {
			System.out.println(product);
		}


	}
}


