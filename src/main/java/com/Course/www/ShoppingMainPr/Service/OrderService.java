package com.Course.www.ShoppingMainPr.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

import jakarta.transaction.Transactional;


@Service
public class OrderService {
	
	private CustomerRepository customerRepository;
	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;
	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;

	public OrderService(CustomerRepository customerRepository, CartRepository cartRepository,
			OrderRepository orderRepository, OrderItemRepository orderItemRepository, CartItemRepository cartItemRepository) {
		super();
		this.customerRepository = customerRepository;
		this.cartRepository = cartRepository;
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
		this.cartItemRepository = cartItemRepository;
	}

	@Transactional
	public Order addingnewone(int customerid) {
		
		Optional<Customer> foundcustomer = customerRepository.findById(customerid);
		if(foundcustomer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABEL"); // checking customer
		
		Optional<Cart> foundcart = cartRepository.findByCustomerCustomerId(customerid);
		if(foundcart.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMERID CART IS NOT AVAILABLE"); // checking customer cart
		
		int ok = foundcart.get().getCartId();
		
		List<CartItem> foundcartitems = cartItemRepository.findByCartCartId(ok);
		if(foundcartitems.isEmpty())
			throw new UserNotFoundException("GIVEN CARTID CARTITEMS ARE NOT AVAILABLE"); 
		
		
		Order order = new Order();
		Customer customer = foundcustomer.get();
		order.setCustomer(customer);
		
		
		int BillingAmount = 0;
		int hello = foundcart.get().getFinalamount();
		int discountprice = hello * 20/100;
		int Good = hello-discountprice;
		BillingAmount = BillingAmount + Good;
		order.setTotalAmount(Good);
		
		
		Order savedOrder = orderRepository.save(order);

		
		for (CartItem item : foundcartitems) {
		    OrderItem orderItem = new OrderItem();
		    orderItem.setOrder(savedOrder);           
		    orderItem.setProduct(item.getProduct());  
		    orderItem.setQuantity(item.getQuantity()); 
		    orderItem.setPrice(item.getPrice());       
		    orderItemRepository.save(orderItem);
		}
		
		for (CartItem items : foundcartitems) {
			int world = items.getCartItemId();
			
			cartItemRepository.deleteById(world);
		}
		foundcart.get().setFinalamount(0);
		cartRepository.save(foundcart.get());
		
		
		
		return savedOrder;
		}
	

	public List<Order> showmyorders(int customerid) {
		
		Optional<Customer> foundcustomer = customerRepository.findById(customerid);
		if(foundcustomer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMERID IS NOT AVALABLE"); // checking customer
		
		List<Order> foundorder = orderRepository.findByCustomerCustomerId(customerid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMERID IS NOT AVAILABLE IN ORDERS"); // checking customer orders
		
		
		return foundorder;
	}

	public Order updatestatus(int customerid, int orderid, Order updateorder) {
		
		Optional<Customer> foundcustomer = customerRepository.findById(customerid);
		if(foundcustomer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMERID IS NOT AVAIBLE"); // checking customer
		
		Optional<Order> foundorder = orderRepository.findById(orderid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE"); // checking order
		
		int m = foundcustomer.get().getCustomerId();
		int n = foundorder.get().getCustomer().getCustomerId();
		Order hai = foundorder.get();
		if (m == n) {
			hai.setOrderStatus(updateorder.getOrderStatus());
			return orderRepository.save(hai);
		}
		throw new UserNotFoundException("CUSTOMERID AND ORDERID BOTH ARE NOT MATCHING");
	
		
	}

}
