package com.donation.donation_form_info.model;

import java.util.List;

import com.donation.adopt_info.model.Adopt_infoVO;
import com.donation.donation_form_info.model.Donation_form_infoVO;
import com.donation.donation_result.model.Donation_resultVO;

public class Donation_form_infoService {
	
	private Donation_form_infoDAO_interface dao;
	
	public Donation_form_infoService() {
		
		dao = new Donation_form_infoJDBCDAO();
	}
	
	public Donation_form_infoVO addDonation_form(String donator_name, String donator_phone_num , Integer donation_money, String payment, String receipt_type, String npo_id) {
	
	Donation_form_infoVO donation_form_infoVO = new Donation_form_infoVO();
	
	donation_form_infoVO.setDonator_name(donator_name);
	donation_form_infoVO.setDonation_money(donation_money);
	donation_form_infoVO.setPayment(payment);
	donation_form_infoVO.setDonator_phone_num(donator_phone_num);
	donation_form_infoVO.setReceipt_type(receipt_type);
	donation_form_infoVO.setNpo_id(npo_id);

	dao.insert(donation_form_infoVO);
	
	return donation_form_infoVO;
	}
	public Donation_form_infoVO updateDonation_form(String donator_name, Integer donation_money, String payment, String donator_phone_num, String receipt_type, String npo_id) {
		
		Donation_form_infoVO donation_form_infoVO = new Donation_form_infoVO();
		
		donation_form_infoVO.setDonator_name(donator_name);
		donation_form_infoVO.setDonation_money(donation_money);
		donation_form_infoVO.setPayment(payment);
		donation_form_infoVO.setDonator_phone_num(donator_phone_num);
		donation_form_infoVO.setReceipt_type(receipt_type);
		donation_form_infoVO.setNpo_id(npo_id);

		dao.insert(donation_form_infoVO);

		return donation_form_infoVO;
	}
	public List<Donation_form_infoVO> getAll(){
		return dao.getAll();
	}
	public Donation_form_infoVO getOneDonation_form_info(String donator_name,String donation_phone_num) {
		return dao.findByPrimaryKey(donator_name,donation_phone_num);
	}
	public List<Donation_form_infoVO> getSelect(String donator_name,String donation_phone_num){
		return dao.getSelect(donator_name, donation_phone_num);
	}
}
