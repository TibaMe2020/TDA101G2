package com.test;

import java.util.List;

import com.store_order_detail.model.Store_order_detailDAO;
import com.store_order_detail.model.Store_order_detailVO;

public class Store_order_detailDAO_Test {

	public static void main(String[] args) {
		Store_order_detailDAO detail = new Store_order_detailDAO();
		// insert
//		Store_order_detailVO st1 = new Store_order_detailVO();
//		st1.setStore_order_id("SO05008");
//		st1.setService_id("SE07008");
//		st1.setOrder_detail_pets(1);
//		detail.insert(st1);
//		System.out.println("新增= "+st1.getStore_order_detail_id()+" "+st1.getStore_order_id()+" "+st1.getService_id()+"*"+st1.getOrder_detail_pets());
		
		// update
//		Store_order_detailVO st2 = new Store_order_detailVO();
//		st2.setStore_order_detail_id("100");
//		st2.setStore_order_id("9");
//		st2.setService_id("9");
//		st2.setOrder_detail_pets(9);
//		detail.update(st2);
//		System.out.println("修改= "+st2.getStore_order_detail_id()+" "+st2.getStore_order_id()+" "+st2.getService_id()+"*"+st2.getOrder_detail_pets());
		
		// delete
//		detail.delete("101");
//		System.out.println("刪除成功");
		
		// selectByOrderId
//		List<Store_order_detailVO> list1=detail.selectByOrderId("10");
//		System.out.println("店家");
//		for(Store_order_detailVO list: list1) {
//			String deid = list.getStore_order_detail_id();
//			String serid = list.getService_id();
//			int pets = list.getOrder_detail_pets();
//			System.out.println(deid+" "+serid+"*"+pets);
//		}
	}

}
