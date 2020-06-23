package com.donation.donation_form_info.model;

import java.util.List;

public interface Donation_form_infoDAO_interface  {
	public void insert(Donation_form_infoVO donation_form_infoVO);
	public void update(Donation_form_infoVO donation_form_infoVO);
//	public void delete(String donation_form_id);
	public Donation_form_infoVO findByPrimaryKey(String donator_name,String donation_phone_num);
	public List<Donation_form_infoVO> getAll();
	public List<Donation_form_infoVO> getSelect(String donator_name,String donation_phone_num);

}
