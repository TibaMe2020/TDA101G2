package com.blog.saved.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SavedVO implements Serializable{
	private String saved_post_id;
	private String member_id;
	private String post_id;
	private Timestamp create_time;
	
	public String getSaved_post_id() {
		return saved_post_id;
	}
	public void setSaved_post_id(String saved_post_id) {
		this.saved_post_id = saved_post_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	@Override
	public String toString() {
		return "[saved_post_id=" + this.saved_post_id + 
			   "  member_id=" + this.member_id +
			   "  post_id=" + this.post_id + 
			   "  create_time=" + this.create_time + "]";
	}
	
}
