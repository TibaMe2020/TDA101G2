package com.store_order.model;

import java.sql.Date;

public class Store_orderVO {
	private String store_order_id;
	private String store_id;
	private String member_id;
	private String store_order_name;
	private String store_order_email;
	private String store_order_phone_num;
	private Date store_order_date_time;
	private Date store_order_end_date;
	private Integer store_order_persons;
	private String store_order_payment;
	private String store_order_note;
	private Integer store_order_state;
	private Date create_time;
	
	public String getStore_order_id() {
		return store_order_id;
	}
	public void setStore_order_id(String store_order_id) {
		this.store_order_id = store_order_id;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getStore_order_name() {
		return store_order_name;
	}
	public void setStore_order_name(String store_order_name) {
		this.store_order_name = store_order_name;
	}
	public String getStore_order_email() {
		return store_order_email;
	}
	public void setStore_order_email(String store_order_email) {
		this.store_order_email = store_order_email;
	}
	public String getStore_order_phone_num() {
		return store_order_phone_num;
	}
	public void setStore_order_phone_num(String store_order_phone_num) {
		this.store_order_phone_num = store_order_phone_num;
	}
	public Date getStore_order_date_time() {
		return store_order_date_time;
	}
	public void setStore_order_date_time(Date store_order_date_time) {
		this.store_order_date_time = store_order_date_time;
	}
	public Date getStore_order_end_date() {
		return store_order_end_date;
	}
	public void setStore_order_end_date(Date store_order_end_date) {
		this.store_order_end_date = store_order_end_date;
	}
	public Integer getStore_order_persons() {
		return store_order_persons;
	}
	public void setStore_order_persons(Integer store_order_persons) {
		this.store_order_persons = store_order_persons;
	}
	public String getStore_order_payment() {
		return store_order_payment;
	}
	public void setStore_order_payment(String store_order_payment) {
		this.store_order_payment = store_order_payment;
	}
	public String getStore_order_note() {
		return store_order_note;
	}
	public void setStore_order_note(String store_order_note) {
		this.store_order_note = store_order_note;
	}
	public Integer getStore_order_state() {
		return store_order_state;
	}
	public void setStore_order_state(Integer store_order_state) {
		this.store_order_state = store_order_state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
