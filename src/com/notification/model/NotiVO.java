package com.notification.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class NotiVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String notification_id;
	private String member_id;
	private Integer notification_class;
	private Timestamp create_time;

	@Override
	public String toString() {
		return "NotiVO [notification_id=" + notification_id + ", member_id=" + member_id + ", notification_class="
				+ notification_class + ", create_time=" + create_time + "]";
	}

	public String getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Integer getNotification_class() {
		return notification_class;
	}

	public void setNotification_class(Integer notification_class) {
		this.notification_class = notification_class;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}
