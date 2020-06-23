package com.donation.donation_result.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class Donation_resultVO implements java.io.Serializable {
	@Override
	public String toString() {
		return "Donation_resultVO [result_id=" + result_id + ", npo_id=" + npo_id + ", member_id=" + member_id
				+ ", result_month=" + result_month + ", result_image=" + Arrays.toString(result_image)
				+ ", result_content=" + result_content + ", create_time=" + create_time + ", update_time=" + update_time
				+ "]";
	}
	private String result_id;
	private String npo_id;
	private String member_id;
	private Integer result_month;
	private byte[] result_image;
	private String  result_content;
	private Timestamp  create_time;
	private Timestamp  update_time;


	
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
	public String getResult_id() {
		return result_id;
	}
	public void setResult_id(String result_id) {
		this.result_id = result_id;
	}
	public String getNpo_id() {
		return npo_id;
	}
	public void setNpo_id(String npo_id) {
		this.npo_id = npo_id;
	}
	public Integer getResult_month() {
		return result_month;
	}
	public void setResult_month(Integer result_month) {
		this.result_month = result_month;
	}
	public byte[] getResult_image() {
		return result_image;
	}
	public void setResult_image(byte[] result_image) {
		this.result_image = result_image;
	}
	public String getResult_content() {
		return result_content;
	}
	public void setResult_content(String result_content) {
		this.result_content = result_content;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


}
