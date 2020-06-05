package com.store_order_detail.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Store_order_detailService {
	private Store_order_detail_interface dao;
	
	public Store_order_detailService() {
		dao = new Store_order_detailDAO(); 
	}
	
	public void newDetail(Store_order_detailVO store_order_detailVO) {
		dao.insert(store_order_detailVO);
	}
	
	public void updateDetail(Store_order_detailVO store_order_detailVO) {
		dao.update(store_order_detailVO);
	}
	
	public void deleteDeatil(String store_order_detail_id) {
		dao.delete(store_order_detail_id);
	}
	
	public List<Store_order_detailVO> selectByOrderId(String store_order_id){
		List<Store_order_detailVO> detail = new ArrayList<Store_order_detailVO>();
		detail = dao.selectByOrderId(store_order_id);
		return detail;
	}
	public void insert2(Store_order_detailVO store_order_detailVO, Connection conn) {
		dao.insert2(store_order_detailVO, conn);
	}
}
