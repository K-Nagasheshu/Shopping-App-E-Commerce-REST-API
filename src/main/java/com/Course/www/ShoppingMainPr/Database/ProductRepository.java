package com.Course.www.ShoppingMainPr.Database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
