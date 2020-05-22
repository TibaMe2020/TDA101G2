package com.store_order_detail.model;

import java.util.List;

import com.store_order.model.Store_orderVO;

public interface Store_order_detail_interface {
	public void insert(Store_order_detailVO store_order_detailVO);
	public void update(Store_order_detailVO store_order_detailVO);
	public void delete(String store_order_detail_id);
	public List<Store_order_detailVO> selectByOrderId(String store_order_id); 
}
