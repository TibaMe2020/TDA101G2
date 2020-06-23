package com.donation.donation_result.model;

import java.util.List;

public interface Donation_resultDAO_interface {
	public void insert(Donation_resultVO donation_resultVO);
//	public void update(Donation_resultVO donation_resultVO);
	public void delete(String result_id);
	public Donation_resultVO findByPrimaryKey(String result_id);
	public List<Donation_resultVO> getMonth(Integer result_month);
	public List<Donation_resultVO> getAll();
	
	

}
