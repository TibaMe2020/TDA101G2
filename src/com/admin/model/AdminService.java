package com.admin.model;

import java.util.List;

import com.member.security.HashPassword;

public class AdminService {
	AdminDAO_interface dao = null;
	
	public AdminService() {
		dao = new AdminDAO();
	}
	
	private static String hashPassword(String password) {
		String algorithm = "SHA-256";
		HashPassword hp = new HashPassword();
		String hashedPassword = hp.generateHash(password, algorithm);
		return hashedPassword;
	}
	
	public AdminVO login(String admin_account, String admin_password) {
		String hashed_password = hashPassword(admin_password);
		try {
			AdminVO adminVO = dao.findByAccount(admin_account);
			if(adminVO == null) return null;
			String stored_password = adminVO.getAdmin_password();
			if(hashed_password.equals(stored_password) && adminVO.getSuspension() != 1) {
				return adminVO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean signup (AdminVO adminVO) {
		try {
			String account = adminVO.getAdmin_account();
			List<AdminVO>admins = dao.getAll();
			for(AdminVO ad : admins) {
				if(account.equals(ad.getAdmin_account())) {
					return false;				
				}
			}
			
			String password = hashPassword(adminVO.getAdmin_password());
			adminVO.setAdmin_password(password);
			adminVO.setSuspension(0);
			dao.insert(adminVO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	
	public AdminVO getOne(String admin_id) {
		return dao.findByPK(admin_id);
	}
	
	public AdminVO suspend(String admin_id) {
		try {
			AdminVO adminVO = dao.findByPK(admin_id);
			if(adminVO.getSuspension() == 0) {
				adminVO.setSuspension(1);
			} else {
				adminVO.setSuspension(0);
			}
			dao.update(adminVO);
			return adminVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean delete(String admin_id) {
		try {
			dao.delete(admin_id);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
