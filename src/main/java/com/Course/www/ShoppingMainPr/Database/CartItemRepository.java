package com.Course.www.ShoppingMainPr.Database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.Cart;
import com.Course.www.ShoppingMainPr.Main.CartItem;
import com.Course.www.ShoppingMainPr.Main.Customer;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	
	List<CartItem> findByCartCartId(int cartId);
	
	void deleteByCartCartIdAndProductProductId(int cartId, int productId);

	List<CartItem> findByCart(Cart cart);

	

}
