package com.donation.npo_info.model;

import java.util.List;

public interface Npo_infoDAO_interface {

	public void insert(Npo_infoVO npo_infoVO);
	public void update(Npo_infoVO npo_infoVO);
	public void delete(String npo_id);
	public Npo_infoVO findByPrimaryKey(String npo_id);
//	public Integer findByDonationMoney(String npo_id);
	public Integer findByDonationMoney(String npo_id);

	public List<Npo_infoVO> getAll();
//	public Set<Npo_inVO> get
}
