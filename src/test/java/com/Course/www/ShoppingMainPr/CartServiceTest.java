package com.Course.www.ShoppingMainPr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
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
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;
import com.Course.www.ShoppingMainPr.Service.CartService;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
	
	@Mock private CartItemRepository cartItemRepository;
	@Mock private CartRepository cartRepository;
	@Mock private CustomerRepository customerRepository;
	@Mock private ProductRepository productRepository;
	@InjectMocks CartService cartService;
	
	@Test
	public void getcarttest() {
		
		Customer dummycustomer = new Customer();
		dummycustomer.setCustomerId(1);
		
		Cart dummycart = new Cart();
		dummycart.setCartId(1);
		dummycart.setCustomer(dummycustomer);
		dummycart.setFinalamount(0);
		
		CartItem dummycartitem = new CartItem();
		dummycartitem.setCart(dummycart);
		dummycartitem.setCartItemId(1);
		dummycartitem.setPrice(1000);
		
		when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Arrays.asList(dummycartitem));
    	when(cartRepository.save(any(Cart.class))).thenReturn(dummycart);
    	
    	Cart result = cartService.getcart(1);
    	
    	assertNotNull(result);
    	assertEquals(1000, result.getFinalamount());
    	
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)). findByCartCartId(1);
    	verify(cartRepository, times(1)).save(any(Cart.class));
	}
	@Test
	public void getcart_cartnotfound() {
		when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartService.getcart(1);
    	});
    	verify(cartRepository, times(1)).findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(0)).findByCartCartId(1);
	}
	@Test
	public void getcart_cartitemnotfound() {
		
		Cart dummycart = new Cart();
		dummycart.setCartId(1);
		dummycart.setFinalamount(0);
		
		when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
		when(cartItemRepository.findByCartCartId(1)).thenReturn(Collections.EMPTY_LIST);
    	assertThrows(UserNotFoundException.class, () -> {
    		cartService.getcart(1);
    	});
    	verify(cartRepository, times(1)).findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)).findByCartCartId(1);
	}
	@Test
	public void deleteitemtest() {
		
		CartItem dummycartitem = new CartItem();
		dummycartitem.setCartItemId(1);
		dummycartitem.setPrice(1000);
		
		when(cartItemRepository.findById(1)).thenReturn(Optional.of(dummycartitem));
		
		cartService.deleteitem(1);
		verify(cartItemRepository, times(1)).deleteById(1);
	}
	@Test
	public void deleteitem_itemnotfound() {
		
		CartItem dummycartitem = new CartItem();
		dummycartitem.setCartItemId(1);
		dummycartitem.setPrice(1000);
		
		when(cartItemRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		cartService.deleteitem(1);
    	});
    	verify(cartItemRepository, times(1)).findById(1);
    	verify(cartItemRepository, times(0)).deleteById(1);
	}
 }
