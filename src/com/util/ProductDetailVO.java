package com.util;

import java.io.Serializable;

public class ProductDetailVO implements Serializable {
	@Override
	public String toString() {
		return "ProductDetailVO [product_order_id=" + product_order_id + ", product_name=" + product_name
				+ ", version_name=" + version_name + ", price=" + price + ", quantity=" + quantity + "]";
	}
	private static final long serialVersionUID = 1L;
	private String product_order_id;
	private String product_name;
	private String version_name;
	private Integer price;
	private Integer quantity;
	public String getProduct_order_id() {
		return product_order_id;
	}
	public void setProduct_order_id(String product_order_id) {
		this.product_order_id = product_order_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
