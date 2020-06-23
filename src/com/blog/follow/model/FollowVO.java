package com.blog.follow.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FollowVO implements Serializable{
	private String follow_id;
	private String member_id;
	private String followed_member_id;
	private Timestamp create_time;
	
	public String getFollow_id() {
		return follow_id;
	}
	public void setFollow_id(String follow_id) {
		this.follow_id = follow_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getFollowed_member_id() {
		return followed_member_id;
	}
	public void setFollowed_member_id(String followed_member_id) {
		this.followed_member_id = followed_member_id;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "[follow_id=" + this.follow_id + 
			   "  member_id=" + this.member_id + 
			   "  followed_member_id=" + this.followed_member_id + 
			   "  create_time=" + this.create_time + "]";
	}
}
