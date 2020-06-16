package com.store.test;

import java.sql.Date;
import java.util.List;

import com.store_order.model.Store_orderDAO;
import com.store_order.model.Store_orderVO;

public class Store_orderDAO_Test {
	
	public static void main(String[] args) {
		Store_orderDAO order = new Store_orderDAO();
		// insert
		Store_orderVO st1 = new Store_orderVO();
//		st1.setStore_order_date_time(new java.sql.Date(System.currentTimeMillis()));
//		st1.setStore_order_payment("信用卡");
//		st1.setStore_id("S07001");
//		order.insert(st1);
//		System.out.println("新增成功="+st1.getStore_id()+" 支付="+st1.getStore_order_payment()+" 訂單="+st1.getStore_order_id()+" 日期="+ st1.getStore_order_date_time());
		
		// update
//		Store_orderVO st2 = new Store_orderVO();
//		st2.setStore_order_id("16");
//		st2.setStore_order_date_time(new java.sql.Date(System.currentTimeMillis()+1000*60*60*24*10));
//		st2.setStore_order_payment("paypal");
//		st2.setStore_order_email("12312@gmail.com");
//		st2.setStore_id("3");
//		order.update(st2);
//		System.out.println("新增成功="+st2.getStore_id()+" 支付="+st2.getStore_order_payment()+" 訂單="+st2.getStore_order_id()+" 日期="+ st2.getStore_order_date_time()+" email="+st2.getStore_order_email());
		
		// delete
//		Store_orderVO st3 = new Store_orderVO();
//		order.delete("16");
//		System.out.println("刪除成功");
		
		// selectByStore
//		List<Store_orderVO> list1 = order.selectByStore("10");
//		System.out.println("店家編號10的訂單有：");
//		for(Store_orderVO list: list1) {
//			String id= list.getStore_order_id();
//			String name =list.getStore_order_name();
//			String num = list.getStore_order_phone_num();
//			int person = list.getStore_order_persons();
//			System.out.println(id+" "+name+" "+num+" "+person);
			
		// selectByMember
//			List<Store_orderVO> list2 = order.selectByMember("10");
//			System.out.println("會員編號10的有");
//			for(Store_orderVO list3: list2) {
//				String id2= list3.getStore_order_id();
//				String name2 =list3.getStore_order_name();
//				String num2 = list3.getStore_order_phone_num();
//				int person2 = list3.getStore_order_persons();
//				System.out.println(id2+" "+name2+" "+num2+" "+person2);	
//			}
		// getAll
		List<Store_orderVO> list4 = order.getAll();
		System.out.println("列出所有訂單");
		for(Store_orderVO list5: list4) {
			String id2= list5.getStore_order_id();
			String name2 =list5.getStore_order_name();
			String num2 = list5.getStore_order_phone_num();
			int person2 = list5.getStore_order_persons();
			System.out.println(id2+" "+name2+" "+num2+" "+person2);	
		}
	}
}	

