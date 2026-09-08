package com.Course.www.ShoppingMainPr.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Service.ProductService;

@RestController
public class ProductController {
	
	
	     private ProductService productService;
	     public ProductController(ProductService productService) {
			super();
			this.productService = productService;
		}
         
	     @PostMapping("/addingproduct")
		 public Product addingnewproduct(@RequestBody Product newproduct) {
			return productService.addingproduct(newproduct);
	     }
	     
	     @GetMapping("/getallproducts")
	     public List<Product> getAll() {
			return productService.showAllProducts();
	     }
	     
	     @GetMapping("/product/{productid}")
	     public Product getProductbyId(@PathVariable int productid) {
			return productService.getProduct(productid);
	     }
	     
	     @PutMapping("/product/{productid}")
	     public Product updateProductName(@PathVariable int productid, @RequestBody Product newproduct) {
			return productService.updateProductName(productid, newproduct);
	     }
	     
	     @DeleteMapping("/product/{productid}")
	     public void deleteProduct(@PathVariable int productid) {
			 productService.deleteProduct(productid);
	     }

}
