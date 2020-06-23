package com.donation.adopt_info.model;

import java.util.List;

import com.donation.adopt_info.model.Adopt_infoService;
import com.donation.adopt_info.model.Adopt_infoVO;

public class Adopt_infoService {
	
	private Adopt_infoDAO_interface dao;
	
	public Adopt_infoService() {
		dao = new Adopt_infoJDBCDAO();
	}
	public Adopt_infoVO addAdopt_info(String adopt_name, byte[] adopt_image, String adopt_description, Integer adopt_money) {
		
		Adopt_infoVO adopt_infoVO = new Adopt_infoVO();
		
		adopt_infoVO.setAdopt_name(adopt_name);
		adopt_infoVO.setAdopt_image(adopt_image);
		adopt_infoVO.setAdopt_description(adopt_description);
		adopt_infoVO.setAdopt_money(adopt_money);
		dao.insert(adopt_infoVO);
		
		return adopt_infoVO;
	}

	
	public Adopt_infoVO updateAdopt_info(String adopt_id, String adopt_name, byte[] adopt_image, String adopt_description) {
		
		Adopt_infoVO adopt_infoVO = new Adopt_infoVO();
		
		adopt_infoVO.setAdopt_id(adopt_id);
		adopt_infoVO.setAdopt_name(adopt_name);
		adopt_infoVO.setAdopt_image(adopt_image);
		adopt_infoVO.setAdopt_description(adopt_description);
//		adopt_infoVO.setAdopt_money(adopt_money);
		dao.update(adopt_infoVO);
		
		return adopt_infoVO;
	}
	public void deleteAdopt_info(String adopt_id) {
		dao.delete(adopt_id);
	}
	public Adopt_infoVO getOneAdopt_info(String adopt_id) {
		return dao.findByPrimaryKey(adopt_id);
	}
	
	public List<Adopt_infoVO> getAll(){
		return dao.getAll();
	}
	
	
}



