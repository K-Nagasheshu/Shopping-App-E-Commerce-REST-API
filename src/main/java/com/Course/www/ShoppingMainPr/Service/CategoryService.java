package com.Course.www.ShoppingMainPr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.Course.www.ShoppingMainPr.Database.CategoryRepository;
import com.Course.www.ShoppingMainPr.Main.Category;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public Category addingCustomer(Category newcategory) {
		return categoryRepository.save(newcategory);
	}

	
	public List<Category> getall() {
		 return categoryRepository.findAll();
	}

	
	public Category getCateorybyid(int categoryid) {
		
		Optional<Category> foundcostmer = categoryRepository.findById(categoryid);
		if(foundcostmer.isEmpty())
			throw new UserNotFoundException("GIVEN CATEGORYID IS NOT AVAILABLE");
		
		return foundcostmer.get();
	}
	
	

}
