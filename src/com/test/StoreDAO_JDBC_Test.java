package com.test;

import java.sql.Date;
import java.util.List;

import com.store.model.StoreDAO_JDBC;
import com.store.model.StoreVO;

public class StoreDAO_JDBC_Test {

	
	public static void main(String[] args) {
		StoreDAO_JDBC storeDao = new StoreDAO_JDBC();
		
		// insert setInt 不能null
//		StoreVO st2 = new StoreVO();
//		st2.setStore_name("毛毛豆豆");
//		st2.setStore_class("餐廳");
//		st2.setStore_clicks(3);
//		st2.setStore_firstbreak(2);
//		st2.setStore_secondbreak(2);
//		st2.setStore_maxcapacity(30);
//		st2.setStore_timelimit(60);
//		st2.setStore_on(0);
//		st2.setUpdate_time(new java.sql.Date(System.currentTimeMillis()));
//		storeDao.insert(st2);
//		System.out.println("新增="+ st2.getStore_id()+ " " + st2.getStore_name() + " " + st2.getStore_class());
//		System.out.println(st2.getUpdate_time());
				
		// update
//		StoreVO st3 = new StoreVO();
//		st3.setStore_id("100");
//		st3.setStore_name("錢錢胖胖");
//		st3.setStore_class("旅館");
//		storeDao.update(st3);
//		System.out.println("修改: " +  st3.getStore_id()+ " " + st3.getStore_name()+ " " + st3.getStore_class());
		
		// delete 
//		storeDao.delete("100");
//		System.out.println("刪除成功");
		
		// findByPK
//		StoreVO st1 = storeDao.findByPK("S07001");
//		String id = st1.getStore_id();
//		String name = st1.getStore_name();
//		Date date = st1.getUpdate_time();
//		System.out.println("查詢id= " + id + " " +name+ " " +date);
		
		// findByClass
//		List<StoreVO> st4 = storeDao.findByClass("美容");
//		for(StoreVO list: st4) {
//			String id4 = list.getStore_id();
//			String name4 = list.getStore_name();
//			int int4 = list.getStore_timelimit();
//			System.out.println("id= " + id4 + " " +name4+ " " +int4);
//		}
		
		
		// getAll
//		List<StoreVO> st5 = storeDao.getAll();
//		for(StoreVO list: st5) {
//			String id5 = list.getStore_id();
//			String name5 = list.getStore_name();
//			System.out.println("id= " + id5 + " " +name5);
//		}
		
	}
	
}
