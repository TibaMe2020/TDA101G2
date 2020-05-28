package com.store.model;

import java.sql.Date;
import java.util.Arrays;

public class StoreVO {
	@Override
	public String toString() {
		return "StoreVO [store_id=" + store_id + ", member_id=" + member_id + ", store_class=" + store_class
				+ ", store_name=" + store_name + ", store_adress=" + store_adress + ", store_phone_number="
				+ store_phone_number + ", store_introduction=" + store_introduction + ", store_clicks=" + store_clicks
				+ ", store_firstbreak=" + store_firstbreak + ", store_secondbreak=" + store_secondbreak
				+ ", store_openhours1=" + store_openhours1 + ", store_openhours2=" + store_openhours2
				+ ", store_openhours3=" + store_openhours3 + ", store_timelimit=" + store_timelimit
				+ ", store_maxcapacity=" + store_maxcapacity + ", store_image1=" + Arrays.toString(store_image1)
				+ ", store_image2=" + Arrays.toString(store_image2) + ", store_image3=" + Arrays.toString(store_image3)
				+ ", store_image4=" + Arrays.toString(store_image4) + ", store_image5=" + Arrays.toString(store_image5)
				+ ", store_image6=" + Arrays.toString(store_image6) + ", store_menu1=" + Arrays.toString(store_menu1)
				+ ", store_menu2=" + Arrays.toString(store_menu2) + ", store_menu3=" + Arrays.toString(store_menu3)
				+ ", store_on=" + store_on + ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
	private String store_id;
	private String member_id;
	private String store_class;
	private String store_name;
	private String store_adress;
	private String store_phone_number;
	private String store_introduction;
	private Integer store_clicks;
	private Integer store_firstbreak;
	private Integer store_secondbreak;
	private String store_openhours1;
	private String store_openhours2;
	private String store_openhours3;
	private Integer store_timelimit;
	private Integer store_maxcapacity;
	private byte[] store_image1;
	private byte[] store_image2;
	private byte[] store_image3;
	private byte[] store_image4;
	private byte[] store_image5;
	private byte[] store_image6;
	private byte[] store_menu1;
	private byte[] store_menu2;
	private byte[] store_menu3;
	private Integer store_on;
	private Date create_time;
	private Date update_time;
	
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
	public String getStore_class() {
		return store_class;
	}
	public void setStore_class(String store_class) {
		this.store_class = store_class;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_adress() {
		return store_adress;
	}
	public void setStore_adress(String store_adress) {
		this.store_adress = store_adress;
	}
	public String getStore_phone_number() {
		return store_phone_number;
	}
	public void setStore_phone_number(String store_phone_number) {
		this.store_phone_number = store_phone_number;
	}
	public String getStore_introduction() {
		return store_introduction;
	}
	public void setStore_introduction(String store_introduction) {
		this.store_introduction = store_introduction;
	}
	public Integer getStore_clicks() {
		return store_clicks;
	}
	public void setStore_clicks(Integer store_clicks) {
		this.store_clicks = store_clicks;
	}
	public Integer getStore_firstbreak() {
		return store_firstbreak;
	}
	public void setStore_firstbreak(Integer store_firstbreak) {
		this.store_firstbreak = store_firstbreak;
	}
	public Integer getStore_secondbreak() {
		return store_secondbreak;
	}
	public void setStore_secondbreak(Integer store_secondbreak) {
		this.store_secondbreak = store_secondbreak;
	}
	public String getStore_openhours1() {
		return store_openhours1;
	}
	public void setStore_openhours1(String store_openhours1) {
		this.store_openhours1 = store_openhours1;
	}
	public String getStore_openhours2() {
		return store_openhours2;
	}
	public void setStore_openhours2(String store_openhours2) {
		this.store_openhours2 = store_openhours2;
	}
	public String getStore_openhours3() {
		return store_openhours3;
	}
	public void setStore_openhours3(String store_openhours3) {
		this.store_openhours3 = store_openhours3;
	}
	public Integer getStore_timelimit() {
		return store_timelimit;
	}
	public void setStore_timelimit(Integer store_timelimit) {
		this.store_timelimit = store_timelimit;
	}
	public Integer getStore_maxcapacity() {
		return store_maxcapacity;
	}
	public void setStore_maxcapacity(Integer store_maxcapacity) {
		this.store_maxcapacity = store_maxcapacity;
	}
	public byte[] getStore_image1() {
		return store_image1;
	}
	public void setStore_image1(byte[] store_image1) {
		this.store_image1 = store_image1;
	}
	public byte[] getStore_image2() {
		return store_image2;
	}
	public void setStore_image2(byte[] store_image2) {
		this.store_image2 = store_image2;
	}
	public byte[] getStore_image3() {
		return store_image3;
	}
	public void setStore_image3(byte[] store_image3) {
		this.store_image3 = store_image3;
	}
	public byte[] getStore_image4() {
		return store_image4;
	}
	public void setStore_image4(byte[] store_image4) {
		this.store_image4 = store_image4;
	}
	public byte[] getStore_image5() {
		return store_image5;
	}
	public void setStore_image5(byte[] store_image5) {
		this.store_image5 = store_image5;
	}
	public byte[] getStore_image6() {
		return store_image6;
	}
	public void setStore_image6(byte[] store_image6) {
		this.store_image6 = store_image6;
	}
	public byte[] getStore_menu1() {
		return store_menu1;
	}
	public void setStore_menu1(byte[] store_menu1) {
		this.store_menu1 = store_menu1;
	}
	public byte[] getStore_menu2() {
		return store_menu2;
	}
	public void setStore_menu2(byte[] store_menu2) {
		this.store_menu2 = store_menu2;
	}
	public byte[] getStore_menu3() {
		return store_menu3;
	}
	public void setStore_menu3(byte[] store_menu3) {
		this.store_menu3 = store_menu3;
	}
	public Integer getStore_on() {
		return store_on;
	}
	public void setStore_on(Integer store_on) {
		this.store_on = store_on;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
