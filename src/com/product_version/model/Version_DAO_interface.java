package com.product_version.model;

import java.util.List;

public interface Version_DAO_interface {

	public void insert(Version_VO version_VO);
	public void update(Version_VO version_VO);
	public void delete(String product_version_id);
	public Version_VO findByPrimaryKey (String product_version_id);
	public List<Version_VO> getAll();
	public List<Version_VO> getbyProductID(String product_id);
	
	public void insert2(Version_VO version_VO,java.sql.Connection con);
	
	
}
