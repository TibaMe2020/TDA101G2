package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import com.store.model.StoreDAO_JDBC;
import com.store.model.StoreVO;

public class StoreDAO_JDBC_Test {

	
	public static void main(String[] args) {
		StoreDAO_JDBC storeDao = new StoreDAO_JDBC();
		
//		try {
//			byte[] image;
//			File input = new File("C:\\Users\\User\\Desktop\\Java雲端服務TDA101\\Agile-陳怡榕\\貓咪.jpg");
//			FileInputStream fis = new FileInputStream(input);
//			image = new byte[fis.available()];
//			fis.read(image);
//		
//			// insert setInt 不能null
//			StoreVO st2 = new StoreVO();
//			st2.setMember_id("MB00033");
//			st2.setStore_name("毛毛豆豆");
//			st2.setStore_class("旅館");
//			st2.setStore_clicks(3);
//			st2.setStore_firstbreak(2);
//			st2.setStore_secondbreak(2);
//			st2.setStore_maxcapacity(30);
//			st2.setStore_timelimit(60);
//			st2.setStore_on(0);
//			st2.setUpdate_time(new java.sql.Date(System.currentTimeMillis()));
//			st2.setStore_image1(image);
//			st2.setStore_image2(image);
//			st2.setStore_image3(image);
////			storeDao.insert(st2);
////			st2.setStore_id("S00052");
////			storeDao.update(st2);
//			System.out.println("新增="+ st2.getMember_id()+ " " + st2.getStore_name() + " " + st2.getStore_class());
//			System.out.println(st2.getUpdate_time());
//			
//			fis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 		
		
		// update
//		StoreVO st3 = new StoreVO();
//		st3.setStore_id("S07001");
//		st3.setStore_name("錢錢胖胖");
//		st3.setStore_class("旅館");
//		storeDao.update(st3);
//		System.out.println("修改: " +  st3.getStore_id()+ " " + st3.getStore_name()+ " " + st3.getStore_class());
		
		// delete 
//		storeDao.delete("100");
//		System.out.println("刪除成功");
		
		// findByPK
//		StoreVO st1 = storeDao.findByPK("S00021");
//		String id = st1.getStore_id();
//		String name = st1.getStore_name();
//		String class2 = st1.getStore_class();
//		Date date = st1.getUpdate_time();
//		System.out.println("查詢id=" + id + " " +name+ " " +class2+" "+date);
		
		// findByMemberId
//		StoreVO st1 = storeDao.findByMemberId("MB01005");
//		String id = st1.getStore_id();
//		String name = st1.getStore_name();
//		Date date = st1.getUpdate_time();
//		System.out.println("查詢id= " + id + " " +name+ " " +date);
		
		// findByMemberId-NullPointerException
//		try {
//		System.out.println(storeDao.findByMemberId("MB01005").getStore_id());
//		}catch(java.lang.NullPointerException e) {
//			System.out.println("MB01005不存在");
//		}
		
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
