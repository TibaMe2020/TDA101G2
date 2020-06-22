package com.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class MemberVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String member_id;
	private String email;
	private String password;
	private String name;
	private String sex;
	private String address;
	private Date birthday;
	private String phone_num;
	private byte[] profile_image;
	private String nickname;
	private String pet_class;
	private byte[] blog_cover_image;
	private String blog_name;
	private Timestamp create_time;
	private Timestamp update_time;
	private Integer member_state;
	private String id_number;
	private String bank_account;
	private byte[] document_image;
	private Timestamp application_time;

	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", sex=" + sex + ", address=" + address + ", birthday=" + birthday + ", phone_num=" + phone_num
				+ ", profile_image=" + Arrays.toString(profile_image) + ", nickname=" + nickname + ", pet_class=" + pet_class
				+ ", blog_cover_image=" + Arrays.toString(blog_cover_image) + ", blog_name=" + blog_name + ", create_time="
				+ create_time + ", update_time=" + update_time + ", member_state=" + member_state + ", id_number=" + id_number
				+ ", bank_account=" + bank_account + ", document_image=" + Arrays.toString(document_image)
				+ ", application_time=" + application_time + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public byte[] getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(byte[] profile_image) {
		this.profile_image = profile_image;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPet_class() {
		return pet_class;
	}

	public void setPet_class(String pet_class) {
		this.pet_class = pet_class;
	}

	public byte[] getBlog_cover_image() {
		return blog_cover_image;
	}

	public void setBlog_cover_image(byte[] blog_cover_image) {
		this.blog_cover_image = blog_cover_image;
	}

	public String getBlog_name() {
		return blog_name;
	}

	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
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

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Integer getMember_state() {
		return member_state;
	}

	public void setMember_state(Integer member_state) {
		this.member_state = member_state;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public byte[] getDocument_image() {
		return document_image;
	}

	public void setDocument_image(byte[] document_image) {
		this.document_image = document_image;
	}

	public Timestamp getApplication_time() {
		return application_time;
	}

	public void setApplication_time(Timestamp application_time) {
		this.application_time = application_time;
	}

}
