package com.donation.adopt_form_info.model;

import java.util.List;

import com.donation.adopt_form_info.model.Adopt_form_infoVO;

public class Adopt_form_infoService {
	
	private Adopt_form_infoDAO_interface dao;
	
	public Adopt_form_infoService() {
		dao = new Adopt_form_infoJDBCDAO();
	}
	public Adopt_form_infoVO addAdopt_form(String adopt_id, String adopt_person, String adopt_talk, String payadopt_person, String adopt_phone_num, String adopt_payment,String adopt_certificate, String adopt_email, String address, String member_id) {
	
	Adopt_form_infoVO adopt_form_infoVO = new Adopt_form_infoVO();
	
	adopt_form_infoVO.setAdopt_id(adopt_id);
	adopt_form_infoVO.setAdopt_person(adopt_person);
	adopt_form_infoVO.setAdopt_talk(adopt_talk);
	adopt_form_infoVO.setPayadopt_person(payadopt_person);
	adopt_form_infoVO.setAdopt_payment(adopt_payment);
	adopt_form_infoVO.setAdopt_certificate(adopt_certificate);
	adopt_form_infoVO.setAdopt_phone_num(adopt_phone_num);
	adopt_form_infoVO.setAdopt_email(adopt_email);
	adopt_form_infoVO.setAddress(address);
	adopt_form_infoVO.setMember_id(member_id);

	dao.insert(adopt_form_infoVO);
	
	return adopt_form_infoVO;
	}
	
	public List<Adopt_form_infoVO> getAll(){
		return dao.getAll();
	}
	
	public Adopt_form_infoVO getOneAdopt_form_info(String adopt_form_id) {
		return dao.findByPrimaryKey(adopt_form_id);
	}
	
	
}
