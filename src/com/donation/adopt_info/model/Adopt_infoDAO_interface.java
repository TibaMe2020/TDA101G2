package com.donation.adopt_info.model;

import java.util.List;

public interface Adopt_infoDAO_interface {
	public void insert(Adopt_infoVO adopt_infoVO);
	public void update(Adopt_infoVO adopt_infoVO);
	public void delete(String adopt_infoVO);
	public Adopt_infoVO findByPrimaryKey(String adopt_id);
	public List<Adopt_infoVO> getAll();
}
