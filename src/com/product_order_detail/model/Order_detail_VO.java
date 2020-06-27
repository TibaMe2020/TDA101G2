package com.product_order_detail.model;

import com.product_version.model.Version_VO;

public class Order_detail_VO {
	private String product_order_detail_id;
	private String product_order_id;
	private String product_version_id;
	private Integer quantity;
	
	//D新增兩個field(惟揚)
	private String seller_id;
	private String product_id;
	
	
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	private Version_VO versionVO;
	
	
	
	@Override
	public String toString() {
		return "Order_detail_VO [product_order_detail_id=" + product_order_detail_id + ", product_order_id="
				+ product_order_id + ", product_version_id=" + product_version_id + ", quantity=" + quantity + ", seller_id="
				+ seller_id + ", product_id=" + product_id + ", versionVO=" + versionVO + "]";
	}
	public Version_VO getVersionVO() {
		return versionVO;
	}
	public void setVersionVO(Version_VO versionVO) {
		this.versionVO = versionVO;
	}
	public String getProduct_order_detail_id() {
		return product_order_detail_id;
	}
	public void setProduct_order_detail_id(String product_order_detail_id) {
		this.product_order_detail_id = product_order_detail_id;
	}
	public String getProduct_order_id() {
		return product_order_id;
	}
	public void setProduct_order_id(String product_order_id) {
		this.product_order_id = product_order_id;
	}
	public String getProduct_version_id() {
		return product_version_id;
	}
	public void setProduct_version_id(String product_version_id) {
		this.product_version_id = product_version_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	

}
