package com.test;

import java.sql.Date;
import java.util.Set;

import com.store_closed.model.Store_closedDAO_JDBC;
import com.store_closed.model.Store_closedVO;

public class Store_closedDAO_JDBC_Test {
	public static void main(String[] args) {
		Store_closedDAO_JDBC closed = new Store_closedDAO_JDBC();
		// insert
		Store_closedVO stcl = new Store_closedVO();
		stcl.setStore_id("S07010");
		stcl.setStore_closed_day(new java.sql.Date(System.currentTimeMillis()));;
		closed.insert(stcl);
		System.out.println("新增休假： " + stcl.getStore_closed_id() + " " + stcl.getStore_id() + " " + stcl.getStore_closed_day());
		
		// delete
//		closed.delete("100");
//		System.out.println("成功刪除");
		
		// selectByStore
//		Set<Store_closedVO> set = closed.selectByStore("10");
//		System.out.println("的休假日是：");
//		for(Store_closedVO list: set) {
//			String id = list.getStore_closed_id();
//			Date date = list.getStore_closed_day();
//			System.out.println("編號=" + id + " 日期= " + date);
//		}
	}
}
