package com.product_order_master.model;

import java.util.List;

import com.product_order_detail.model.Order_detail_VO;

public class Order_master_Service {
	private Order_master_DAO_interface dao;

	public Order_master_Service() {
		dao = new Order_master_DAO();
	}

	public Order_master_VO addOrder_master(Order_master_VO order_master_VO, List<Order_detail_VO> list) {
		dao.insertWithDetail(order_master_VO, list);
		return order_master_VO;
	}

	public Order_master_VO getOneMaster(String product_order_master_id) {
		return dao.findByPrimaryKey(product_order_master_id);

	}
	public List<Order_master_VO> getAll(){
		return dao.getAll();
		
	}

	public List<Order_master_VO> getAllmember(String member_id){
		return dao.getAllmember(member_id);

	}
	
	public void updateState(String order_master_id, String order_state) {
		dao.updateState(order_master_id, order_state);
	}

}
