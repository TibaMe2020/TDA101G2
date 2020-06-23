package com.donation.donation_result.model;

import java.util.List;

import com.donation.donation_result.model.Donation_resultVO;

public class Donation_resultService {
		
	private Donation_resultDAO_interface dao;
	
	public Donation_resultService() {
		dao = new Donation_resultJDBCDAO();
	}
	public Donation_resultVO addDonation_result(Integer result_month, byte[] result_image, String result_content, String npo_id) {
		
		Donation_resultVO donation_resultVO = new Donation_resultVO();
		
		donation_resultVO.setResult_month(result_month); 
		donation_resultVO.setResult_image(result_image);
		donation_resultVO.setResult_content(result_content);
		donation_resultVO.setNpo_id(npo_id);


		dao.insert(donation_resultVO);
		
		return donation_resultVO;	
	}
	public List<Donation_resultVO> getAll(){
		return dao.getAll();
	}
	
	public void deleteDonation_result(String reult_id) {
		dao.delete(reult_id);
	}
	public Donation_resultVO getOneDonation_result(String result_id) {
		return dao.findByPrimaryKey(result_id);
	}
	public List<Donation_resultVO> getMonth(Integer result_month){
		return dao.getMonth(result_month);
	}
}



