package com.product_order_master.model;

import java.sql.Date;
import java.util.List;

import com.product_order_detail.model.Order_detail_VO;

public class Order_master_VO {

	private String order_master_id;
	private String member_id;
	private String product_order_state;
	private String payment;
	private String location;//門市資訊
	private Date create_time;
	List<Order_detail_VO> detail_list;
	
	public List<Order_detail_VO> getDetail_list() {
		return detail_list;
	}
	public void setDetail_list(List<Order_detail_VO> detail_list) {
		this.detail_list = detail_list;
	}
	
	public Order_master_VO setDetails(List<Order_detail_VO> detail_list) {
		this.detail_list = detail_list;
		return this;
	}
	
	@Override
	public String toString() {
		return "Order_master_VO [order_master_id=" + order_master_id + ", member_id=" + member_id + ", product_order_state="
				+ product_order_state + ", payment=" + payment + ", location=" + location + ", create_time=" + create_time
				+ ", detail_list=" + detail_list + "]";
	}

	public String getOrder_master_id() {
		return order_master_id;
	}
	public void setOrder_master_id(String order_master_id) {
		this.order_master_id = order_master_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getProduct_order_state() {
		return product_order_state;
	}
	public void setProduct_order_state(String product_order_state) {
		this.product_order_state = product_order_state;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}
