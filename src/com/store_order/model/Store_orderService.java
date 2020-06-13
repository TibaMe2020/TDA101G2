package com.store_order.model;

import java.util.ArrayList;
import java.util.List;

import com.store_order_detail.model.Store_order_detailVO;

public class Store_orderService {
	private Store_orderDAO_interface dao;
	
	public Store_orderService() {
		dao = new Store_orderDAO();
	}
	
	public void newOrder(Store_orderVO store_orderVO) {
		dao.insert(store_orderVO);
	}
	
	public void updateOrder(Store_orderVO store_orderVO) {
		dao.update(store_orderVO);
	}
	
	public void deleteOrder(String store_order_id) {
		dao.delete(store_order_id);
	}
	
	public List<Store_orderVO> selectByStore(String store_id){
		List<Store_orderVO> order = new ArrayList<Store_orderVO>();
		order = dao.selectByStore(store_id);
		return order; 
	}
	
	public List<Store_orderVO> selectByMember(String member_id){
		List<Store_orderVO> order = new ArrayList<Store_orderVO>();
		order = dao.selectByMember(member_id);
		return order; 
	}
	
	public List<Store_orderVO> getAll(){
		List<Store_orderVO> order = new ArrayList<Store_orderVO>();
		order = dao.getAll();
		return order; 
	}
	public void insertWithDetail(Store_orderVO store_orderVO, List<Store_order_detailVO> list) {
		dao.insertWithDetail(store_orderVO, list);
	}
	public String insertWithDetail_return(Store_orderVO store_orderVO, List<Store_order_detailVO> list) {
		return dao.insertWithDetail_return(store_orderVO, list);
	}
}
