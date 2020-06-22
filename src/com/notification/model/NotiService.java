package com.notification.model;

import java.util.List;

public class NotiService {
	private NotiDAO_interface dao;
	
	public NotiService() {
		dao = new NotiDAO();
	}
	
	public List<NotiVO> findByMembId(String member_id) {
		return dao.findByMembId(member_id);
	}

	public NotiVO findByPK(String notification_id) {
		return dao.findByPK(notification_id);
	}
	
	public NotiVO insert(NotiVO notiVO) {
		dao.insert(notiVO);
		return notiVO;
	}
	
	public void update (NotiVO notiVO) {
		dao.update(notiVO);
	}
	
	public boolean delete(String notification_id) {
		try {
			dao.delete(notification_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
 }
