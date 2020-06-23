package com.donation.adopt_form_info.model;

import java.util.List;

public interface Adopt_form_infoDAO_interface {
	public void insert(Adopt_form_infoVO adopt_form_infoVO);
//	public void update(Adopt_form_infoVO adopt_form_infoVO);
//	public void delete(String adopt_form_id);
	public Adopt_form_infoVO findByPrimaryKey(String adopt_form_id);
	public List<Adopt_form_infoVO> getAll();
}
