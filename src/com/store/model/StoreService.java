package com.store.model;

import java.util.List;

import com.abstractInterface.MemberVO;

public class StoreService {
	
	private StoreDAO_interface dao;
	
	public StoreService() {
		dao = new StoreDAO_JDBC();
	}
	
	public boolean checkMemberState(MemberVO memberVO) {
		// 店家認證
		if(memberVO.getMember_state() == 2)
			return true;
		return false;
	}
	
	public StoreVO matchMemberId(MemberVO memberVO) {
		String memberId = memberVO.getMember_id();
		try {
		// 回傳店家
		return dao.findByMemberId(memberId);
			
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
	
	public StoreVO newStore(String store_id, String member_id, String store_class, String store_name,
			String store_adress, String store_phone_number,	String store_introduction, Integer store_clicks,
			Integer store_firstbreak, Integer store_secondbreak, String store_openhours1, String store_openhours2,
			String store_openhours3, Integer store_timelimit, Integer store_maxcapacity, byte[] store_image1,
			byte[] store_image2, byte[] store_image3, byte[] store_image4, byte[] store_image5, byte[] store_image6,
			byte[] store_menu1, byte[] store_menu2, byte[] store_menu3, Integer store_on) {
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_id(store_id);
		storeVO.setMember_id(member_id);
		storeVO.setStore_class(store_class);
		storeVO.setStore_name(store_name);
		storeVO.setStore_adress(store_adress);
		storeVO.setStore_phone_number(store_phone_number);
		storeVO.setStore_introduction(store_introduction);
		storeVO.setStore_clicks(store_clicks);
		storeVO.setStore_firstbreak(store_firstbreak);
		storeVO.setStore_secondbreak(store_secondbreak);
		storeVO.setStore_openhours1(store_openhours1);
		storeVO.setStore_openhours2(store_openhours2);
		storeVO.setStore_openhours3(store_openhours3);
		storeVO.setStore_timelimit(store_timelimit);
		storeVO.setStore_maxcapacity(store_maxcapacity);
		storeVO.setStore_image1(store_image1);
		storeVO.setStore_image2(store_image2);
		storeVO.setStore_image3(store_image3);
		storeVO.setStore_image4(store_image4);
		storeVO.setStore_image5(store_image5);
		storeVO.setStore_image6(store_image6);
		storeVO.setStore_menu1(store_menu1);
		storeVO.setStore_menu2(store_menu2);
		storeVO.setStore_menu3(store_menu3);
		storeVO.setStore_on(store_on);
		dao.insert(storeVO);
		
		return storeVO;
	}
	
	public StoreVO updateStore(String store_id, String member_id, String store_class, String store_name,
			String store_adress, String store_phone_number,	String store_introduction, Integer store_clicks,
			Integer store_firstbreak, Integer store_secondbreak, String store_openhours1, String store_openhours2,
			String store_openhours3, Integer store_timelimit, Integer store_maxcapacity, byte[] store_image1,
			byte[] store_image2, byte[] store_image3, byte[] store_image4, byte[] store_image5, byte[] store_image6,
			byte[] store_menu1, byte[] store_menu2, byte[] store_menu3, Integer store_on) {
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_id(store_id);
		storeVO.setMember_id(member_id);
		storeVO.setStore_class(store_class);
		storeVO.setStore_name(store_name);
		storeVO.setStore_adress(store_adress);
		storeVO.setStore_phone_number(store_phone_number);
		storeVO.setStore_introduction(store_introduction);
		storeVO.setStore_clicks(store_clicks);
		storeVO.setStore_firstbreak(store_firstbreak);
		storeVO.setStore_secondbreak(store_secondbreak);
		storeVO.setStore_openhours1(store_openhours1);
		storeVO.setStore_openhours2(store_openhours2);
		storeVO.setStore_openhours3(store_openhours3);
		storeVO.setStore_timelimit(store_timelimit);
		storeVO.setStore_maxcapacity(store_maxcapacity);
		storeVO.setStore_image1(store_image1);
		storeVO.setStore_image2(store_image2);
		storeVO.setStore_image3(store_image3);
		storeVO.setStore_image4(store_image4);
		storeVO.setStore_image5(store_image5);
		storeVO.setStore_image6(store_image6);
		storeVO.setStore_menu1(store_menu1);
		storeVO.setStore_menu2(store_menu2);
		storeVO.setStore_menu3(store_menu3);
		storeVO.setStore_on(store_on);
		storeVO.setUpdate_time(new java.sql.Date(System.currentTimeMillis()));
		dao.update(storeVO);
		
		return storeVO;
	}
	
	public void deleteStore(String store_id) {
		dao.delete(store_id);
	}
	
	public StoreVO findStoreId(String store_id) {
		return dao.findByPK(store_id);
	}
		
	public List<StoreVO> findByClass(String store_class){
		return dao.findByClass(store_class);
	}
	
	public List<StoreVO> getAll(){
		return dao.getAll();
	}
}
