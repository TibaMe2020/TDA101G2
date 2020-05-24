package com.store_closed.model;

import java.util.Set;

public interface Store_closedDAO_interface {
	public void insert(Store_closedVO store_closedVO);
	public void delete(String store_closed_id);
	public Set<Store_closedVO> selectByStore(String store_id);
}
