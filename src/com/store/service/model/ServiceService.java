package com.store.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceService {
	private ServiceDAO_interface dao;
	
	public ServiceService() {
		dao = new ServiceDAO();
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
	public void newSerivce(ServiceVO serviceVO) {
		dao.insert(serviceVO);
	}
	public String newSerivce2(ServiceVO serviceVO) {
		return dao.insert2(serviceVO);
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
	public void updateService(ServiceVO serviceVO) {
		dao.update(serviceVO);
	}
	public void updateServiceState(String service_id, Integer state) {
		ServiceVO serviceVO = dao.selectByServiceID(service_id);
		serviceVO.setService_state(state);
		dao.update(serviceVO);
	}
	
	public void deleteService(String service_id) {
		dao.delete(service_id);
	}

	public List<ServiceVO> selectByStore(String store_id){
		List<ServiceVO> service = new ArrayList<ServiceVO>();
		service = dao.selectByStore(store_id);
		return service;
	}
	
	public List<ServiceVO> selectByStoreFilter(String store_id){
		List<ServiceVO> service = dao.selectByStore(store_id);
		List<ServiceVO> result = new ArrayList<>();
		result = service.stream()
			.filter(s -> s.getService_state() != 0)
			.collect(Collectors.toList());
		return result;
	}
	
	public ServiceVO selectByServiceID(String service_id) {
		return dao.selectByServiceID(service_id);
	}
	
//	public static void main(String[] args) {
//		System.out.println("-----------------------------------");
//		ServiceService dao = new ServiceService();
//		List<ServiceVO> s = dao.selectByStoreFilter("S07004");
//		for(ServiceVO l : s) {
//			System.out.println(l.getService_id()+" "+l.getService_detail()+" "+ l.getService_price());
//		}
//	}
}
