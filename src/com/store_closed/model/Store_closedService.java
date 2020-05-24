package com.store_closed.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.store.model.StoreVO;


public class Store_closedService {
	private Store_closedDAO_interface dao;
	
	public Store_closedService(){
		dao = new Store_closedDAO_JDBC();
	}
	
	public String getMemberId(StoreVO storeVO) {
		return storeVO.getStore_id();
	}
	
	public Store_closedVO newClosed(String store_id, Date store_closed_day) {
		Store_closedVO closedVO = new Store_closedVO();
		closedVO.setStore_closed_id(store_id);
		closedVO.setStore_closed_day(store_closed_day);
		dao.insert(closedVO);
		return closedVO;
	}
	
	public void deleteClosed(String store_closed_id) {
		dao.delete(store_closed_id);
	}
	
	public Set<Store_closedVO> selectByStore(String store_id){
		Set<Store_closedVO> closed = new LinkedHashSet<Store_closedVO>();
		closed = dao.selectByStore(store_id);
		return closed;
	}
	
}
