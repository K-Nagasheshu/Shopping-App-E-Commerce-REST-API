package com.Course.www.ShoppingMainPr.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Course.www.ShoppingMainPr.Main.Category;
import com.Course.www.ShoppingMainPr.Service.CategoryService;

@RestController
public class CategoryController {
	
		 private CategoryService categoryService;

	 public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	 }
	 
	         // create new catoegory 
	         @PostMapping("/newcategory")
	         public Category CategoryaddingCategory(@RequestBody Category newcategory) {
		         return categoryService.addingCustomer(newcategory);
		     }
	         
	        // get all categorys
			@GetMapping("/category")
			public List<Category> getAll() {
				return categoryService.getall();
			}
			
			// get particular category based on category id
			@GetMapping("/category/{categoryid}")
			public Category getCategorybasedonId(@PathVariable int categoryid) {
				return categoryService.getCateorybyid(categoryid);
			}

			
	 
	
	

}
