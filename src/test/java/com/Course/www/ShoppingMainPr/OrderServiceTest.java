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
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.Course.www.ShoppingMainPr.Database.CartItemRepository;
import com.Course.www.ShoppingMainPr.Database.CartRepository;
import com.Course.www.ShoppingMainPr.Database.CustomerRepository;
import com.Course.www.ShoppingMainPr.Database.OrderItemRepository;
import com.Course.www.ShoppingMainPr.Database.OrderRepository;
import com.Course.www.ShoppingMainPr.Main.Cart;
import com.Course.www.ShoppingMainPr.Main.CartItem;
import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Main.Order;
import com.Course.www.ShoppingMainPr.Main.OrderItem;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;
import com.Course.www.ShoppingMainPr.Service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock private CustomerRepository customerRepository;
	@Mock private CartRepository cartRepository;
	@Mock private CartItemRepository cartItemRepository;
	@Mock private OrderRepository orderRepository;
	@Mock private OrderItemRepository orderItemRepository;
	@InjectMocks private OrderService orderService;
	
	@Test
	public void testaddingnewone() {
		
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
		Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	dummycart.setFinalamount(100);
    	dummycart.setCustomer(dummycustomer);
    	dummycart.setCartItem(null);
    	
    	Product dummyproduct = new Product();
    	dummyproduct.setProductPrice(100);
    	dummyproduct.setProductId(1);
    	
		CartItem dummycartitem = new CartItem();
    	dummycartitem.setCart(dummycart);
    	dummycartitem.setCartItemId(1);
    	dummycartitem.setPrice(100);
    	dummycartitem.setProduct(dummyproduct);
    	dummycartitem.setQuantity(3);
    	
    	Order dummyorder = new Order();
    	dummyorder.setTotalAmount(80);
    	dummyorder.setCustomer(dummycustomer);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Arrays.asList(dummycartitem));
    	when(orderRepository.save(any(Order.class))).thenReturn(dummyorder);
    	
    	Order result = orderService.addingnewone(1);
    	
    	assertNotNull(result);
    	assertEquals(80, result.getTotalAmount());
    	
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)). findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)).findByCartCartId(1);
    	verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    	verify(cartItemRepository, times(1)).deleteById(1);
    	verify(cartRepository, times(1)).save(dummycart);	
	}
	@Test
	public void addingnewone_customernotfound() {
		when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.addingnewone(1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(0)).findByCustomerCustomerId(1);
     }
	@Test
	public void addingnewone_cartnotfound() {
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
		when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.addingnewone(1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)).findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(0)).findByCartCartId(1);
     }
	@Test
	public void addingnewone_cartitemnotfound() {
		
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	Cart dummycart = new Cart();
    	dummycart.setCartId(1);
    	dummycart.setFinalamount(100);
    	dummycart.setCustomer(dummycustomer);
    	dummycart.setCartItem(null);
    	
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(cartRepository.findByCustomerCustomerId(1)).thenReturn(Optional.of(dummycart));
    	when(cartItemRepository.findByCartCartId(1)).thenReturn(Collections.EMPTY_LIST);
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.addingnewone(1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(cartRepository, times(1)).findByCustomerCustomerId(1);
    	verify(cartItemRepository, times(1)).findByCartCartId(1);
	}
	@Test
	public void testshowmyorders() {
		
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	Order dummyorder = new Order();
    	dummyorder.setTotalAmount(80);
    	dummyorder.setCustomer(dummycustomer);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(orderRepository.findByCustomerCustomerId(1)).thenReturn(Arrays.asList(dummyorder));
    	
        List<Order> result = orderService.showmyorders(1);
    	
    	assertNotNull(result);
    	assertEquals(1, result.size()); 
    	assertEquals(80, result.get(0).getTotalAmount()); 
    	
    	verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(1)). findByCustomerCustomerId(1);
	}
	@Test
	public void showmyorders_customernotfound() {
		when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.showmyorders(1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(0)).findByCustomerCustomerId(1);
     }
	@Test
	public void showmyorders_ordernotfound() {
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
		when(orderRepository.findByCustomerCustomerId(1)).thenReturn(Collections.EMPTY_LIST);
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.showmyorders(1);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(1)).findByCustomerCustomerId(1);
     }
	@Test
	public void testupdatestatus() {
		
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	Order dummyorder = new Order();
    	dummyorder.setOrderId(1);
    	dummyorder.setTotalAmount(80);
    	dummyorder.setCustomer(dummycustomer);
    	dummyorder.setOrderStatus("DELIVERD");
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
    	when(orderRepository.findById(1)).thenReturn(Optional.of(dummyorder));
    	when(orderRepository.save(any(Order.class))).thenReturn(dummyorder);
    	
    	Order result = orderService.updatestatus(1, 1, dummyorder);
    	
    	assertNotNull(result);
    	assertEquals("DELIVERD", result.getOrderStatus());
    	assertEquals(1, result.getOrderId());
    	
    	verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(1)).findById(1);
    	verify(orderRepository, times(1)).save(any(Order.class));
	}
	@Test
	public void updatestatus_customernotfound() {
		when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.updatestatus(1, 1, null);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(0)).findById(1);
     }
	@Test
	public void updatestatus_ordernotfound() {
		Customer dummycustomer = new Customer();
    	dummycustomer.setCustomerId(1);
    	
    	when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
		when(orderRepository.findById(1)).thenReturn(Optional.empty());
    	assertThrows(UserNotFoundException.class, () -> {
    		orderService.updatestatus(1, 1, null);
    	});
    	verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(1)).findById(1);
     }
	@Test
	public void updatestatus_customerorderidmismatch() {
		Customer dummycustomer = new Customer();
		dummycustomer.setCustomerId(1);
		
		Customer OtheCustomer = new Customer();
		OtheCustomer.setCustomerId(2);
		
		Order dummyorder = new Order();
        dummyorder.setOrderId(1);
		dummyorder.setCustomer(OtheCustomer);
		
		when(customerRepository.findById(1)).thenReturn(Optional.of(dummycustomer));
		when(orderRepository.findById(1)).thenReturn(Optional.of(dummyorder));
		
		assertThrows(UserNotFoundException.class, () -> {
    		orderService.updatestatus(1, 1, null);
    	});
		
		verify(customerRepository, times(1)).findById(1);
    	verify(orderRepository, times(1)).findById(1);
    	verify(orderRepository, times(0)).save(any());
	}
}
