package com.store_order.model;

import java.util.List;

import com.store_order_detail.model.Store_order_detailVO;

public interface Store_orderDAO_interface {
	public void insert(Store_orderVO store_orderVO);
	public void update(Store_orderVO store_orderVO);
	public void delete(String store_order_id);
	public List<Store_orderVO> selectByStore(String store_id); 
	public List<Store_orderVO> selectByMember(String member_id); 
	public List<Store_orderVO> getAll();
	public void insertWithDetail(Store_orderVO store_orderVO, List<Store_order_detailVO> list);
}
