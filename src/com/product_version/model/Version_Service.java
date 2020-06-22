package com.product_version.model;

import java.sql.Connection;
import java.util.List;

public class Version_Service {
	private Version_DAO_interface dao;

	public Version_Service() {
		dao = new Version_DAO();
	}

	public Version_VO addVersion(Version_VO version_VO) {
		dao.insert(version_VO);
		return version_VO;
	}

	public Version_VO updateVersion(Version_VO version_VO) {
		dao.update(version_VO);
		return version_VO;

	}

	public void deletVersion(String product_version_id) {
		dao.delete(product_version_id);

	}

	public Version_VO getOneVersion(String product_version_id) {
		return dao.findByPrimaryKey(product_version_id);

	}

	public List<Version_VO> getAll() {
		return dao.getAll();

	}
	
	public List<Version_VO> getbyProductID(String product_id) {
		return dao.getbyProductID(product_id);

	}
	
	public Version_VO addVersion2(Version_VO version_VO,Connection con) {
		dao.insert2(version_VO, con);
		return version_VO;

	}
	
//	public static void main(String[] args) {
//		List<Version_VO> getbyProductID = new Version_Service().getbyProductID("PID00001");
//		System.out.println(getbyProductID.size());
//		getbyProductID.forEach(System.out::println);
//		
//	}
	
}
