package com.admin.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String admin_id;
	private String admin_account;
	private String admin_password;
	private Integer suspension;
	private Timestamp create_time;
	private Timestamp update_time;

	@Override
	public String toString() {
		return "AdminVO [admin_id=" + admin_id + ", admin_account=" + admin_account + ", admin_password=" + admin_password
				+ ", suspension=" + suspension + ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public Integer getSuspension() {
		return suspension;
	}

	public void setSuspension(Integer suspension) {
		this.suspension = suspension;
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
