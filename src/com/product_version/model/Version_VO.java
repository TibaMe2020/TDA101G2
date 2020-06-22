package com.product_version.model;

import com.product.model.Product_VO;

public class Version_VO implements java.io.Serializable{

	private String product_version_id;
	private String product_id;
	private String version_name;
	private Integer price;
	private Integer inventory;
	
	private Product_VO productVO;
	
	public Product_VO getProductVO() {
		return productVO;
	}
	public void setProductVO(Product_VO productVO) {
		this.productVO = productVO;
	}
	@Override
	public String toString() {
		return "Version_VO [product_version_id=" + product_version_id + ", product_id=" + product_id + ", version_name="
				+ version_name + ", price=" + price + ", inventory=" + inventory + "]";
	}
	public String getProduct_version_id() {
		return product_version_id;
	}
	public void setProduct_version_id(String product_version_id) {
		this.product_version_id = product_version_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	
	
	
}
