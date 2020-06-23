package com.store.service.model;

import java.util.List;

public interface ServiceDAO_interface {
	public void insert(ServiceVO serviceVO);
	public void update(ServiceVO serviceVO);
	public void delete(String service_id);
	public String insert2(ServiceVO serviceVO);
	public ServiceVO selectByServiceID(String service_id);
	public List<ServiceVO> selectByStore(String store_id);
	
}
