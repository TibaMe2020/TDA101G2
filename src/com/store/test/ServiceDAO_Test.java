package com.store.test;

import java.util.List;

import com.store.service.model.ServiceDAO;
import com.store.service.model.ServiceVO;

public class ServiceDAO_Test {
	public static void main(String[] args) {
		
		ServiceDAO serviceDao = new ServiceDAO(); 
		// insert
//		ServiceVO ser1 = new ServiceVO();
//		ser1.setStore_id("S07008");
//		ser1.setService_price(8000);
//		ser1.setService_detail("寵物名店");
//		ser1.setService_limit(-1);
//		serviceDao.insert(ser1);
//		System.out.println("新增成功= "+ser1.getService_id()+" 價格= "+ser1.getService_price()+
//				" 描述= "+ser1.getService_detail()+" limit= "+ser1.getService_limit());
		
		// update
//		ServiceVO ser2 = new ServiceVO();
//		ser2.setService_id("100");
//		ser2.setService_detail("旺旺喵喵");
//		ser2.setStore_id("8");
//		ser2.setService_price(2500);
//		serviceDao.update(ser2);
//		System.out.println("修改成功= "+ser2.getService_id()+" 價格= "+ser2.getService_price()+" 描述= "+ser2.getService_detail());
		
		// delete
//		ServiceVO ser3 = new ServiceVO();
//		serviceDao.delete("101");
//		System.out.println("刪除成功");
		
		// selectByStore
		List<ServiceVO> ser4 = serviceDao.selectByStore("S07008");
		for(ServiceVO list:ser4) {
			String id = list.getService_id();
			String detail = list.getService_detail();
			int price = list.getService_price();
			int limit = list.getService_limit();
			System.out.println("id="+id+" price="+price+" detail="+detail+" limit="+limit);
		}
	
	}
}
