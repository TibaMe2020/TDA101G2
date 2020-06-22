package com.product_order_detail.model;

import java.util.List;

public interface Order_detail_DAO_interface {

	public void insert(Order_detail_VO order_detail_VO);

	public Order_detail_VO findByPrimaryKey(String product_order_detail_id);

	public List<Order_detail_VO> getAll();
	
	//同時新增主檔與明細
	public void insert2(Order_detail_VO order_detail_VO,java.sql.Connection con);

	public List<Order_detail_VO> getByOmId(String omId);
}
