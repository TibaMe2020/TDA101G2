package com.store.model;

import java.util.List;

import com.store_closed.model.Store_closedVO;

public interface StoreDAO_interface {
	public void insert(StoreVO storeVO);
	public StoreVO update(StoreVO storeVO);
	public void delete(String store_id);
	public StoreVO findByPK(String store_id);
	public StoreVO findByMemberId(String member_id);
	public List<StoreVO> findByClass(String store_class);
	public List<StoreVO> findByClass2(String store_class); 
	public List<StoreVO> getAll();
	public void insertWithClosed(StoreVO storeVO, List<Store_closedVO> list);
}
