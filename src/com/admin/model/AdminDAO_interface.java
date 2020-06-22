package com.admin.model;

import java.util.List;

public interface AdminDAO_interface {

	public void insert(AdminVO adminVO);

	public void update(AdminVO adminVO);

	public void delete(String admin_id);

	public AdminVO findByPK(String admin_id);

	public AdminVO findByAccount(String admin_account);

	public List<AdminVO> getAll();

}
