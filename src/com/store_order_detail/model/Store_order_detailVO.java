package com.store_order_detail.model;

public class Store_order_detailVO {
	private String store_order_detail_id;
	private String store_order_id;
	private String service_id;
	private Integer order_detail_pets;
	
	public String getStore_order_detail_id() {
		return store_order_detail_id;
	}
	public void setStore_order_detail_id(String store_order_detail_id) {
		this.store_order_detail_id = store_order_detail_id;
	}
	public String getStore_order_id() {
		return store_order_id;
	}
	public void setStore_order_id(String store_order_id) {
		this.store_order_id = store_order_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public Integer getOrder_detail_pets() {
		return order_detail_pets;
	}
	public void setOrder_detail_pets(Integer order_detail_pets) {
		this.order_detail_pets = order_detail_pets;
	}
	
	
}
