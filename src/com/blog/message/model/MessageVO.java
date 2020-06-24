package com.blog.message.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageVO implements Serializable{
	private String message_id;
	private String member_id;
	private String post_id;
	private String message_content;
	private Timestamp create_time;
	private Timestamp update_time;
	
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
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
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
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
		return "[message_id=" + this.message_id + 
			   "  member_id=" + this.member_id + 
			   "  post_id=" + this.post_id + 
			   "\nmassage_content=" + this.message_content + 
			   "\ncreate_time=" + this.create_time + 
			   "  update_time=" + this.update_time + "]";
	}	
}
