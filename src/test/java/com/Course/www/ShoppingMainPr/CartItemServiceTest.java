package com.Course.www.ShoppingMainPr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.Course.www.ShoppingMainPr.Database.CartItemRepository;
import com.Course.www.ShoppingMainPr.Database.CartRepository;
import com.Course.www.ShoppingMainPr.Database.CustomerRepository;
import com.Course.www.ShoppingMainPr.Database.ProductRepository;
import com.Course.www.ShoppingMainPr.Main.Cart;
import com.Course.www.ShoppingMainPr.Main.CartItem;
import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;
import com.Course.www.ShoppingMainPr.Service.CartItemService;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private CartRepository cartRepository;
	@Mock
	private CartItemRepository cartItemRepository;
	@InjectMocks
	private CartItemService cartItemService;
	

    @Test
    public void testaddingprouctsintocart() {
    	
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	
    	CartItem dummycartitem = new CartItem();
    	dummycartitem.setCart(dummycart);
    	dummycartitem.setProduct(dummyproduct);
    	dummycartitem.setQuantity(3);
    	dummycartitem.setPrice(300);
    	
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.save(any(CartItem.class))).thenReturn(dummycartitem);
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Arrays.asList(dummycartitem));
    	
    	
    	CartItem result = cartItemService.addingproductintocart(1, 1, 3);
    	
    	assertNotNull(result);
    	assertEquals(3, result.getQuantity());
    	assertEquals(300, result.getPrice());
    	
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
    
    @Test
    public void addingproductsintocart_customernotfound() {
    	when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.addingproductintocart(1, 1, 3);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(0)).findById(1);
     }
    @Test
    public void addingproductsintocart_productnotfound() {
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.addingproductintocart(1, 1, 3);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(0)). findByCustomerCustomerId(1);
    }
    @Test
    public void addingproductsintocart_cartnotfound() {
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.addingproductintocart(1, 1, 3);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(0)).save(any(CartItem.class));
    }
    
    @Test
    public void testupdatesize() {
    	
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	
    	CartItem dummycartitem = new CartItem();
    	dummycartitem.setCart(dummycart);
    	dummycartitem.setProduct(dummyproduct);
    	dummycartitem.setProductSize(45);
    	dummycartitem.setQuantity(3);
    	dummycartitem.setPrice(300);
    	
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Arrays.asList(dummycartitem));
    	when(cartItemRepository.save(any(CartItem.class))).thenReturn(dummycartitem);
    	
    	CartItem result = cartItemService.updatesize(1, 1, 45);
    	
    	assertNotNull(result);
    	assertEquals(45, result.getProductSize());
    	
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)).findByCartCartId(1);
    	verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
    @Test
    public void updatesize_customernotfound() {
    	when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.updatesize(1, 1, 45);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(0)).findById(1);
     }
    @Test
    public void updatesize_productnotfound() {
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.updatesize(1, 1, 45);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(0)). findByCustomerCustomerId(1);
    }
    @Test
    public void updatesize_cartnotfound() {
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.updatesize(1, 1, 45);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(0)).save(any(CartItem.class));
    }
    @Test
    public void updatesize_cartitemsempty() {
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Collections.EMPTY_LIST);
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.updatesize(1, 1, 45);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    }
    @Test
    public void updatesize_productnotinlist() {
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	
    	Product cartproduct = new Product();
    	cartproduct.setProductId(20);
    	
    	CartItem dummycartitem = new CartItem();
    	dummycartitem.setCart(dummycart);
    	dummycartitem.setProduct(cartproduct);
    	dummycartitem.setQuantity(3);
    	dummycartitem.setPrice(300);
    	dummycartitem.setProductSize(20);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Arrays.asList(dummycartitem));
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.updatesize(1, 1, 45);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)).findByCartCartId(1);
    	verify(cartItemRepository, times(0)).save(any(CartItem.class));
    }
    @Test
    public void testdeleteproduct() {
    	
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	
    	CartItem dummycartitem = new CartItem();
    	dummycartitem.setCart(dummycart);
    	dummycartitem.setProduct(dummyproduct);
    	dummycartitem.setQuantity(3);
    	dummycartitem.setPrice(300);
    	
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(productRepository.findById(1)).thenReturn(Optional.of(dummyproduct));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Arrays.asList(dummycartitem));
    	
    	cartItemService.deleteProduct(1, 1);
    	
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(productRepository, times(1)).findById(1);
    	verify(cartItemRepository, times(1)).deleteByCartCartIdAndProductProductId(1, 1);
    	verify(cartItemRepository, times(1)).findByCartCartId(1);
    	verify(cartRepository, times(1)). save(dummycart);
    }
    @Test
    public void deleteproduct_customernotfound() {
    	when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.deleteProduct(1, 1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(0)). findByCustomerCustomerId(1);
     }
    @Test
    public void deleteproduct_cartnotfound() {
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.deleteProduct(1, 1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(productRepository, times(0)).findById(1);
    }
    @Test
    public void deleteproduct_productnotfound() {
    	Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(productRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartItemService.deleteProduct(1, 1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(productRepository, times(1)).findById(1);
    }
}