package com.store_closed.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.store.model.StoreVO;

public class Store_closedService {
	private Store_closedDAO_interface dao;
	
	public Store_closedService(){
		dao = new Store_closedDAO();
	}
	public void newClosed(Store_closedVO store_closedVO) {
		dao.insert(store_closedVO);
	}
	
	public Store_closedVO newClosed(String store_id, Date store_closed_day) {
		Store_closedVO closedVO = new Store_closedVO();
		closedVO.setStore_id(store_id);
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
	
	public void insert2(Store_closedVO store_closedVO,Connection conn) {
		dao.insert2(store_closedVO, conn);
	}
	
//	public static void main(String[] args) {
//		Store_closedService scs = new Store_closedService();
//		scs.newClosed("S07008", new java.sql.Date(System.currentTimeMillis()));
//		System.out.println("newClose 新增成功");
//		scs.deleteClosed("SC00023");
//		System.out.println("deleteClosed 刪除成功");
//		Set<Store_closedVO>collection = scs.selectByStore("S07010");
//		for(Store_closedVO list : collection) {
//			String id = list.getStore_closed_id();
//			Date date = list.getStore_closed_day();
//			System.out.println(id+" "+date);
//		}
//	}
	
}
