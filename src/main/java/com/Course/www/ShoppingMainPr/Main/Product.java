package com.Course.www.ShoppingMainPr.Main;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private int productId;
	private int discount;
	private String companyName;
	private String productType;
	private String productName;
	private int productPrice;
	private int productSize;
	private int stock;
	
	@JsonBackReference
	@JoinColumn(name ="category_id")
	@ManyToOne
	private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Product() {
		super();
	}
	
	public Product(int productId, int discount, String companyName, String productType, String productName,
			int productPrice, int productSize, int stock, Category category) {
		super();
		this.productId = productId;
		this.discount = discount;
		this.companyName = companyName;
		this.productType = productType;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productSize = productSize;
		this.stock = stock;
		this.category = category;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductSize() {
		return productSize;
	}
	public void setProductSize(int productSize) {
		this.productSize = productSize;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", discount=" + discount + ", companyName=" + companyName
				+ ", productType=" + productType + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productSize=" + productSize + ", stock=" + stock + ", category=" + category + "]";
	}
}