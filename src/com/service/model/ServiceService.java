package com.service.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceService {
	private ServiceDAO_interface dao;
	
	public ServiceService() {
		dao = new ServiceDAO_JDBC();
	}
	
	public ServiceVO newSerivce(String store_id,  String service_detail, Integer service_price,
			Integer service_limit, Integer service_time, Integer service_state) {
		ServiceVO service = new ServiceVO();
		service.setStore_id(store_id);
		service.setService_detail(service_detail);
		service.setService_price(service_price);
		service.setService_limit(service_limit);
		service.setService_time(service_time);
		service.setService_state(service_state);
		dao.insert(service);
		
		return service;
	}
	
	public ServiceVO updateService(String service_id, String store_id,  String service_detail, Integer service_price,
			Integer service_limit, Integer service_time, Integer service_state) {
		ServiceVO service = new ServiceVO();
		service.setService_id(service_id);
		service.setStore_id(store_id);
		service.setService_detail(service_detail);
		service.setService_price(service_price);
		service.setService_limit(service_limit);
		service.setService_time(service_time);
		service.setService_state(service_state);
		dao.update(service);
		
		return service;
	}
	
	public void deleteService(String service_id) {
		dao.delete(service_id);
	}

	public List<ServiceVO> selectByStore(String store_id){
		List<ServiceVO> service = new ArrayList<ServiceVO>();
		service = dao.selectByStore(store_id);
		return service;
	}
}
