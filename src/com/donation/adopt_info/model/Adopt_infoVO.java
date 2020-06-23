package com.donation.adopt_info.model;

import java.sql.Timestamp;

public class Adopt_infoVO implements java.io.Serializable {
	private String adopt_id;
	private byte[] adopt_image;
	private String adopt_name;
	private String adopt_description;
	private Integer adopt_money; //double
	private Timestamp create_time;
	private Timestamp update_time;
	
	public String getAdopt_id() {
		return adopt_id;
	}
	public void setAdopt_id(String adopt_id) {
		this.adopt_id = adopt_id;
	}
	public byte[] getAdopt_image() {
		return adopt_image;
	}
	public void setAdopt_image(byte[] adopt_image) {
		this.adopt_image = adopt_image;
	}
	public String getAdopt_name() {
		return adopt_name;
	}
	public void setAdopt_name(String adopt_name) {
		this.adopt_name = adopt_name;
	}
	public String getAdopt_description() {
		return adopt_description;
	}
	public void setAdopt_description(String adopt_description) {
		this.adopt_description = adopt_description;
	}
	public Integer getAdopt_money() {
		return adopt_money;
	}
	public void setAdopt_money(Integer adopt_money) {
		this.adopt_money = adopt_money;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}


}
