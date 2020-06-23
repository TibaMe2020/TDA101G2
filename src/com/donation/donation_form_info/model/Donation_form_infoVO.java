package com.donation.donation_form_info.model;

import java.sql.Timestamp;

public class Donation_form_infoVO implements java.io.Serializable {
	@Override
	public String toString() {
		return "Donation_form_infoVO [donation_form_id=" + donation_form_id + ", npo_id=" + npo_id + ", member_id="
				+ member_id + ", donator_name=" + donator_name + ", donation_money=" + donation_money + ", payment="
				+ payment + ", donator_phone_num=" + donator_phone_num + ", receipt_type=" + receipt_type
				+ ", create_time=" + create_time + "]";
	}
	private String donation_form_id;
	private String npo_id;
	private String member_id;
	private String donator_name;
	private Integer donation_money; //double
	private String payment;
	private String donator_phone_num;
	private String receipt_type;
	private Timestamp create_time;
	
	public String getDonation_form_id() {
		return donation_form_id;
	}
	public void setDonation_form_id(String donation_form_id) {
		this.donation_form_id = donation_form_id;
	}
	public String getNpo_id() {
		return npo_id;
	}
	public void setNpo_id(String npo_id) {
		this.npo_id = npo_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getDonator_name() {
		return donator_name;
	}
	public void setDonator_name(String donator_name) {
		this.donator_name = donator_name;
	}
	public Integer getDonation_money() {
		return donation_money;
	}
	public void setDonation_money(Integer donation_money) {
		this.donation_money = donation_money;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDonator_phone_num() {
		return donator_phone_num;
	}
	public void setDonator_phone_num(String donator_phone_num) {
		this.donator_phone_num = donator_phone_num;
	}
	public String getReceipt_type() {
		return receipt_type;
	}
	public void setReceipt_type(String receipt_type) {
		this.receipt_type = receipt_type;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
	

}
