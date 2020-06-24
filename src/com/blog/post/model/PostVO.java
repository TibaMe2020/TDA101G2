package com.blog.post.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostVO implements Serializable{
	private String post_id;
	private String member_id;
	private String post_class;
	private byte[] post_image1;
	private byte[] post_image2;
	private byte[] post_image3;
	private byte[] post_image4;
	private byte[] post_image5;
	private String post_content;
	private Integer post_like;
	private Integer post_message_count;
	private Integer post_share;
	private Timestamp create_time;
	private Timestamp update_time;
	
	
	public String getPost_id() {
		return post_id;
	}


	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getPost_class() {
		return post_class;
	}


	public void setPost_class(String post_class) {
		this.post_class = post_class;
	}


	public byte[] getPost_image1() {
		return post_image1;
	}


	public void setPost_image1(byte[] post_image1) {
		this.post_image1 = post_image1;
	}


	public byte[] getPost_image2() {
		return post_image2;
	}


	public void setPost_image2(byte[] post_image2) {
		this.post_image2 = post_image2;
	}


	public byte[] getPost_image3() {
		return post_image3;
	}


	public void setPost_image3(byte[] post_image3) {
		this.post_image3 = post_image3;
	}


	public byte[] getPost_image4() {
		return post_image4;
	}


	public void setPost_image4(byte[] post_image4) {
		this.post_image4 = post_image4;
	}


	public byte[] getPost_image5() {
		return post_image5;
	}


	public void setPost_image5(byte[] post_image5) {
		this.post_image5 = post_image5;
	}


	public String getPost_content() {
		return post_content;
	}


	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}


	public Integer getPost_like() {
		return post_like;
	}


	public void setPost_like(Integer post_like) {
		this.post_like = post_like;
	}


	public Integer getPost_message_count() {
		return post_message_count;
	}


	public void setPost_message_count(Integer post_message_count) {
		this.post_message_count = post_message_count;
	}


	public Integer getPost_share() {
		return post_share;
	}


	public void setPost_share(Integer post_share) {
		this.post_share = post_share;
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


	@Override
	public String toString() {
		return "[post_id=" + this.post_id + 
			   "  member_id=" + this.member_id + 
			   "  post_class=" + this.post_class + 
			   "  post_image1=" + this.post_image1 + 
			   "  post_image2=" + this.post_image2 +
			   "  post_image3=" + this.post_image3 +
			   "  post_image4=" + this.post_image4 +
			   "  post_image5=" + this.post_image5 +
			   "\npost_content=" + this.post_content +
			   "\npost_like=" + this.post_like + 
			   "  post_message_count=" + this.post_message_count + 
			   "  post_share=" + this.post_share +
			   "  create_time=" + this.create_time + 
			   "  update_time=" + this.update_time +
	           "]";
	}
	
}
