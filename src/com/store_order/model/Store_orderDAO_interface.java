package com.store_order.model;

import java.util.List;

public interface Store_orderDAO_interface {
	public void insert(Store_orderVO store_orderVO);
	public void update(Store_orderVO store_orderVO);
	public void delete(String store_order_id);
	public List<Store_orderVO> selectByStore(String store_id); 
	public List<Store_orderVO> selectByMember(String member_id); 
}
