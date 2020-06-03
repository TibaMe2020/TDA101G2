package com.store.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.abstractInterface.MemberVO;

public class StoreService {
	
	private StoreDAO_interface dao;
	
	public StoreService() {
		dao = new StoreDAO();
	}
	
	public String getStoreId(StoreVO storeVO) {
		return storeVO.getStore_id();
	}
	
	public boolean checkMemberState(MemberVO memberVO) {
		// 店家認證
		if(memberVO.getMember_state() == 2)
			return true;
		return false;
	}
	
	public StoreVO matchMemberId(MemberVO memberVO) {
		String member_id = memberVO.getMember_id();
//		String member_id = "MB00001";
		try {
		// 回傳店家
		return dao.findByMemberId(member_id);
			
		}catch(NullPointerException e) {
			// 店家尚未新增
			return null;
		}
	}
	
	public void changeStoreOn(StoreVO storeVO) {
		if(storeVO.getStore_on()==0) {
			storeVO.setStore_on(1);
		}else {
			storeVO.setStore_on(0);
		}
	}
	public StoreVO newStore(StoreVO storeVO) {
		dao.insert(storeVO);
		return storeVO;
	}
	public StoreVO updateStore(StoreVO storeVO) {
		dao.update(storeVO);
		return storeVO;
	}
	// 未更新 不使用 - 圖片只剩三張
//	public StoreVO newStore(				 String member_id, String store_class, String store_name,
//			String store_adress, String store_phone_number,	String store_introduction, Integer store_clicks,
//			Integer store_firstbreak, Integer store_secondbreak, String store_openhours1, String store_openhours2,
//			String store_openhours3, Integer store_timelimit, Integer store_maxcapacity, byte[] store_image1,
//			byte[] store_image2, byte[] store_image3, byte[] store_image4, byte[] store_image5, byte[] store_image6,
//			byte[] store_menu1, byte[] store_menu2, byte[] store_menu3, Integer store_on) {
//		StoreVO storeVO = new StoreVO();
//		storeVO.setMember_id(member_id);
//		storeVO.setStore_class(store_class);
//		storeVO.setStore_name(store_name);
//		storeVO.setStore_adress(store_adress);
//		storeVO.setStore_phone_number(store_phone_number);
//		storeVO.setStore_introduction(store_introduction);
//		storeVO.setStore_clicks(store_clicks);
//		storeVO.setStore_firstbreak(store_firstbreak);
//		storeVO.setStore_secondbreak(store_secondbreak);
//		storeVO.setStore_openhours1(store_openhours1);
//		storeVO.setStore_openhours2(store_openhours2);
//		storeVO.setStore_openhours3(store_openhours3);
//		storeVO.setStore_timelimit(store_timelimit);
//		storeVO.setStore_maxcapacity(store_maxcapacity);
//		storeVO.setStore_image1(store_image1);
//		storeVO.setStore_image2(store_image2);
//		storeVO.setStore_image3(store_image3);
//		storeVO.setStore_image4(store_image4);
//		storeVO.setStore_image5(store_image5);
//		storeVO.setStore_image6(store_image6);
//		storeVO.setStore_menu1(store_menu1);
//		storeVO.setStore_menu2(store_menu2);
//		storeVO.setStore_menu3(store_menu3);
//		storeVO.setStore_on(store_on);
//		dao.insert(storeVO);
//		
//		return storeVO;
//	}
	// 未更新 不使用 - 圖片只剩三張
//	public StoreVO updateStore(String store_id, String member_id, String store_class, String store_name,
//			String store_adress, String store_phone_number,	String store_introduction, Integer store_clicks,
//			Integer store_firstbreak, Integer store_secondbreak, String store_openhours1, String store_openhours2,
//			String store_openhours3, Integer store_timelimit, Integer store_maxcapacity, byte[] store_image1,
//			byte[] store_image2, byte[] store_image3, byte[] store_image4, byte[] store_image5, byte[] store_image6,
//			byte[] store_menu1, byte[] store_menu2, byte[] store_menu3, Integer store_on) {
//		StoreVO storeVO = new StoreVO();
//		storeVO.setStore_id(store_id);
//		storeVO.setMember_id(member_id);
//		storeVO.setStore_class(store_class);
//		storeVO.setStore_name(store_name);
//		storeVO.setStore_adress(store_adress);
//		storeVO.setStore_phone_number(store_phone_number);
//		storeVO.setStore_introduction(store_introduction);
//		storeVO.setStore_clicks(store_clicks);
//		storeVO.setStore_firstbreak(store_firstbreak);
//		storeVO.setStore_secondbreak(store_secondbreak);
//		storeVO.setStore_openhours1(store_openhours1);
//		storeVO.setStore_openhours2(store_openhours2);
//		storeVO.setStore_openhours3(store_openhours3);
//		storeVO.setStore_timelimit(store_timelimit);
//		storeVO.setStore_maxcapacity(store_maxcapacity);
//		storeVO.setStore_image1(store_image1);
//		storeVO.setStore_image2(store_image2);
//		storeVO.setStore_image3(store_image3);
//		storeVO.setStore_image4(store_image4);
//		storeVO.setStore_image5(store_image5);
//		storeVO.setStore_image6(store_image6);
//		storeVO.setStore_menu1(store_menu1);
//		storeVO.setStore_menu2(store_menu2);
//		storeVO.setStore_menu3(store_menu3);
//		storeVO.setStore_on(store_on);
//		storeVO.setUpdate_time(new java.sql.Date(System.currentTimeMillis()));
//		dao.update(storeVO);
//		
//		return storeVO;
//	}
	
	public void deleteStore(String store_id) {
		dao.delete(store_id);
	}
	
	public StoreVO findByStoreId(String store_id) {
		return dao.findByPK(store_id);
	}
		
	public List<StoreVO> findByClass(String store_class){
		return dao.findByClass(store_class);
	}
	
	public List<StoreVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		StoreService ss = new StoreService();
		StoreVO sVO = new StoreVO();
		
		sVO.setStore_id("S00100");
		sVO.setMember_id("MB0055");
		sVO.setStore_class("醫院");
		
		// getStoreId 
//		System.out.println(ss.getStoreId(sVO));
		
		// changeStoreOn 
//		sVO.setStore_on(0);
//		ss.changeStoreOn(sVO);
//		int storeON = sVO.getStore_on();
//		System.out.println(storeON);
		
		// newStore
//		File file = new File("C:\\Users\\user\\Downloads\\2019-10月秋季新番\\PSYCHO-PASS 心靈判官 第三季\\001323zrox39rnq3jiwnmg.jpg");
//        FileInputStream fin = null;
//        try {
//			fin = new FileInputStream(file);
//			byte[] fileContent = new byte[(int)file.length()];
//			System.out.println(fileContent.toString());
//			ss.newStore("S00111", "MB0055", "學校", "曼巴", "板橋大貫路	", "000-000", "小寶萬歲", 500, 2, 5, "", "","",00,00,
//					fileContent, fileContent, fileContent, fileContent, fileContent, fileContent, fileContent, fileContent, fileContent, 1);
//			ss.newStore(		  "MB0055", "學校", "曼巴", "板橋大貫路	", "000-000", "小寶萬歲", 500, 2, 5, "", "","",00,00,
//					null, null, null, null, null, null, null, null, null, 1);
			System.out.println("新增成功");
//		} catch (FileNotFoundException e) {
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
        // deleteStore
//        ss.deleteStore("S00006");
//        System.out.println("deleteStore成功");
        
        // findStoreId
//		sVO = ss.findStoreId("S00021");
//		System.out.println(sVO.getStore_name()+" "+sVO.getStore_class()+" "+sVO.getStore_adress()+" "+sVO.getStore_introduction());
 
		// findByClass
//		List<StoreVO> list1 = ss.findByClass("旅館");
//		for(StoreVO list:list1) {
//			System.out.println(list.getStore_id()+" "+list.getStore_name()+" "+list.getStore_adress()+" "+list.getStore_introduction());
//		}
		
		// getAll
//		List<StoreVO> list2 = ss.getAll();
//		for(StoreVO list:list2) {
//			System.out.println(list.getStore_id()+" "+list.getStore_name()+" "+list.getStore_adress()+" "+list.getStore_introduction());
//		}
		
	}
}
