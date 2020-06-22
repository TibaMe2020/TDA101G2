package com.product_order_master.model;

import java.util.List;
import java.util.Set;

import com.product_order_detail.model.Order_detail_VO;

public interface Order_master_DAO_interface {

	public void insert(Order_master_VO order_detail_VO);

	public Order_master_VO findByPrimaryKey(String product_order_detail_id);

	public List<Order_master_VO> getAll();

	//查詢某會員的訂單
	public List<Order_master_VO> getAllmember(String member_id);

     //查詢某訂單主檔的訂單明細
	public Set<Order_detail_VO> getDetailByMaster(String product_order_id);	
	
	//同時增加主檔跟明細
	public void insertWithDetail(Order_master_VO order_master_VO,List<Order_detail_VO> list);

	public void updateState(String order_master_id, String order_state);

}
