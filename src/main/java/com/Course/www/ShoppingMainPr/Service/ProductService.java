package com.Course.www.ShoppingMainPr.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Course.www.ShoppingMainPr.Database.ProductRepository;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}


	public Product addingproduct(Product newproduct) {
		return productRepository.save(newproduct);
	}


	public List<Product> showAllProducts() {
		return productRepository.findAll();
	}


	public Product getProduct(int productid) {
		
		Optional<Product> foundcostmer = productRepository.findById(productid);
		if(foundcostmer.isEmpty())
			throw new UserNotFoundException("GIVEN PRODUCTID IS NOT AVAILABLE"); // checking customer
		
		Product product = foundcostmer.get();
		return product;
	}


	public Product updateProductName(int productid, Product newproduct) {
		
		Optional<Product> foundcostmer = productRepository.findById(productid);
		if(foundcostmer.isEmpty())
			throw new UserNotFoundException("GIVEN PRODUCTID IS NOT AVAILABLE"); // checking customer
		
		Product hai = foundcostmer.get();
		hai.setProductName(newproduct.getProductName());
		return productRepository.save(hai);
		
	}


	public void deleteProduct(int productid) {
	
		Optional<Product> foundcostmer = productRepository.findById(productid);
		if(foundcostmer.isEmpty())
			throw new UserNotFoundException("GIVEN PRODUCTID IS NOT AVAILABLE"); 
		 productRepository.deleteById(productid);
	}
	
	

}
