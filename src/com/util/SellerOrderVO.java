package com.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class SellerOrderVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String seller_id;
	private String buyer_id;
	private String product_order_id;
	private Integer product_order_state;
	private Timestamp create_time;
	private Integer total;
	private List<ProductDetailVO> product_list;
	
	@Override
	public String toString() {
		return "SellerOrderVO [seller_id=" + seller_id + ", buyer_id=" + buyer_id + ", product_order_id=" + product_order_id
				+ ", product_order_state=" + product_order_state + ", create_time=" + create_time + ", total=" + total
				+ ", product_list=" + product_list + ", getProduct_list()=" + getProduct_list() + ", getSeller_id()="
				+ getSeller_id() + ", getBuyer_id()=" + getBuyer_id() + ", getProduct_order_id()=" + getProduct_order_id()
				+ ", getProduct_order_state()=" + getProduct_order_state() + ", getCreate_time()=" + getCreate_time()
				+ ", getTotal()=" + getTotal();
	}
	
	public List<ProductDetailVO> getProduct_list() {
		return product_list;
	}
	public void setProduct_list(List<ProductDetailVO> product_list) {
		this.product_list = product_list;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getProduct_order_id() {
		return product_order_id;
	}
	public void setProduct_order_id(String product_order_id) {
		this.product_order_id = product_order_id;
	}
	public Integer getProduct_order_state() {
		return product_order_state;
	}
	public void setProduct_order_state(Integer product_order_state) {
		this.product_order_state = product_order_state;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	
}