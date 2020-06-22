package com.product_order_detail.model;

import java.sql.Connection;
import java.util.List;

public class Order_detail_Service {

	private Order_detail_DAO_interface dao;

	public Order_detail_Service() {
		dao = new Order_detail_DAO();
	}

	public Order_detail_VO addOrder_detail(Order_detail_VO order_detail_VO) {
		dao.insert(order_detail_VO);
		return order_detail_VO;

	}

	public Order_detail_VO getOneDetail(String product_order_detail_id) {
		return dao.findByPrimaryKey(product_order_detail_id);

	}
	
	public List<Order_detail_VO> getAll(){
		return dao.getAll();
	}
	
	public List<Order_detail_VO> getByOmId(String omId) {
		return dao.getByOmId(omId);
	}
	
	public Order_detail_VO addOrder_detail2(Order_detail_VO order_detail_VO,Connection con) {
		dao.insert2(order_detail_VO, con);
		return order_detail_VO;

	}
}
