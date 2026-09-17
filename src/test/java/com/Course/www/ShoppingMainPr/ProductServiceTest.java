package com.Course.www.ShoppingMainPr;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.Course.www.ShoppingMainPr.Database.ProductRepository;
import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Main.Order;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;
import com.Course.www.ShoppingMainPr.Service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock ProductRepository productRepository;
	@InjectMocks ProductService productService;
	
	@Test
	public void addingproductest() {
		
		Product dummyproduct = new Product();
		dummyproduct.setProductId(1);
		dummyproduct.setProductName("Mobile");
		dummyproduct.setProductPrice(50000);
		
		Customer dummycustomer = new Customer();
		dummycustomer.setCustomerId(1);
		
		when(productRepository.save(any(Product.class))).thenReturn(dummyproduct);
		Product result = productService.addingproduct(dummyproduct);
		
		assertNotNull(result);
		assertEquals(1, result.getProductId());
		assertEquals(50000, result.getProductPrice());
		
		verify(productRepository, times(1)).save(any());
	}
	@Test
	public void showallproductstest() {
		
		Product dummyproduct1 = new Product();
		dummyproduct1.setProductId(1);
		dummyproduct1.setProductName("Mobile");
		dummyproduct1.setProductPrice(50000);
		
		Product dummyproduct2 = new Product();
		dummyproduct2.setProductId(1);
		dummyproduct2.setProductName("LAPTOP");
		dummyproduct2.setProductPrice(50000);
		
		List<Product> showallproducts = Arrays.asList(dummyproduct1, dummyproduct2);
		
		when(productRepository.findAll()).thenReturn(showallproducts);
		
		List<Product> result = productService.showAllProducts();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Mobile", result.get(0).getProductName());
		
		verify(productRepository, times(1)).findAll();
	}
	@Test
	public void getproducttest() {
		Product dummyproduct = new Product();
		dummyproduct.setProductId(1);
		dummyproduct.setProductName("Mobile");
		dummyproduct.setProductPrice(50000);
		
		Customer dummycustomer = new Customer();
		dummycustomer.setCustomerId(1);
		
		when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
		
		Product result = productService.getProduct(1);
		assertNotNull(result);
		assertEquals("Mobile", result.getProductName());
		
		verify(productRepository, times(1)).findById(1);
	}
	@Test
	public void getproducttest_productnotfound() {
		when(productRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			productService.getProduct(1);
		});
		verify(productRepository, times(1)).findById(1);
	}
	@Test
	public void updateproductnametest() {
		Product dummyproduct1 = new Product();
		dummyproduct1.setProductId(1);
		dummyproduct1.setProductName("Mobile");
		dummyproduct1.setProductPrice(50000);
		
		Product dummyproduct2 = new Product();
		dummyproduct2.setProductId(1);
		dummyproduct2.setProductName("LAPTOP");
		
		when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct1));
		when(productRepository.save(any(Product.class))).thenReturn(dummyproduct1);
		
		Product result = productService.updateProductName(1, dummyproduct2);
		
		assertNotNull(result);
		assertEquals("LAPTOP", result.getProductName());
		
		verify(productRepository, times(1)).save(any());
	}
	@Test
	public void updateproductnametest_productnotfound() { 
		when(productRepository.findById(1)).thenReturn(Optional.empty());
		
	
		assertThrows(UserNotFoundException.class, () -> {
			productService.updateProductName(1, null);
		});
		verify(productRepository, times(1)).findById(1);
	}
	@Test
	public void deleteproducttest() {
		Product dummyproduct = new Product();
		dummyproduct.setProductId(1);
		dummyproduct.setProductName("Mobile");
		dummyproduct.setProductPrice(50000);
		
		when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
		
		productService.deleteProduct(1);
		
		verify(productRepository, times(1)).findById(1);
		verify(productRepository, times(1)).deleteById(1);
	}
	@Test
	public void deleteproducttest_productnotfound() {
		when(productRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			productService.deleteProduct(1);
		});
		verify(productRepository, times(1)).findById(1);
	}
}
