package com.store.model;

import java.util.List;

public interface StoreDAO_interface {
	public void insert(StoreVO storeVO);
	public void update(StoreVO storeVO);
	public void delete(String store_id);
	public StoreVO findByPK(String store_id);
	public List<StoreVO> findByClass(String store_class);
	public List<StoreVO> getAll();
}
