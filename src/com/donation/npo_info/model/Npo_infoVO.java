package com.donation.npo_info.model;

import java.sql.Timestamp;

public class Npo_infoVO implements java.io.Serializable {
	private String npo_id;
	private String npo_name;
	private byte[] npo_image;
	private String npo_description;
	private Integer total;
	private Timestamp create_time;

	public String getNpo_id() {
		return npo_id;
	}
	public void setNpo_id(String npo_id) {
		this.npo_id = npo_id;
	}
	public byte[] getNpo_image() {
		return npo_image;
	}
	public void setNpo_image(byte[] npo_image) {
		this.npo_image = npo_image;
	}
	public String getNpo_name() {
		return npo_name;
	}
	public void setNpo_name(String npo_name) {
		this.npo_name = npo_name;
	}
	public String getNpo_description() {
		return npo_description;
	}
	public void setNpo_description(String npo_description) {
		this.npo_description = npo_description;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	

}
