package com.donation.npo_info.model;

import java.util.List;

public class Npo_infoService {
	
	private Npo_infoDAO_interface dao;
	
	public Npo_infoService() {
		dao = new Npo_infoJDBCDAO(); 
	}
	public Npo_infoVO addNpo_info(String npo_name, byte[] npo_image, String npo_description) {
		
		Npo_infoVO npo_infoVO = new Npo_infoVO();
		
		npo_infoVO.setNpo_name(npo_name);
		npo_infoVO.setNpo_image(npo_image);
		npo_infoVO.setNpo_description(npo_description);
		dao.insert(npo_infoVO);
		
		return npo_infoVO;
	}
	public static void main(String[] args) {
		Npo_infoService npo_infoService = new Npo_infoService();
		System.out.println(npo_infoService.findByDonationMoney("NID00001"));
	}
	
	public Npo_infoVO updateNpo_info(String npo_id, String npo_name, byte[] npo_image, String npo_description) {
		
		Npo_infoVO npo_infoVO = new Npo_infoVO();
		
		npo_infoVO.setNpo_id(npo_id);
		npo_infoVO.setNpo_name(npo_name);
		npo_infoVO.setNpo_image(npo_image);
		npo_infoVO.setNpo_description(npo_description);
		dao.update(npo_infoVO);
		
		return npo_infoVO;
	}
	public void deleteNpo_info(String npo_id) {
		dao.delete(npo_id);
	}
	public Npo_infoVO getOneNpo_info(String npo_id) {
		return dao.findByPrimaryKey(npo_id);
	}
	
	public List<Npo_infoVO> getAll(){
		return dao.getAll();
	}
	public List<Npo_infoVO> getAllmon(){
		
		//
		List<Npo_infoVO> mon = dao.getAll();
		for (Npo_infoVO npo_infoVO : mon) {
			Integer total = dao.findByDonationMoney(npo_infoVO.getNpo_id());
			npo_infoVO.setTotal(total);
		}
		return mon;
	}
	
	public Integer findByDonationMoney(String npo_id) {
		return dao.findByDonationMoney(npo_id);

	}

}

