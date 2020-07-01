package com.store.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Part;

import com.member.model.MemberVO;
import com.store_closed.model.Store_closedVO;

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
		if (memberVO.getMember_state() == 2)
			return true;
		return false;
	}

	public StoreVO matchMemberId(MemberVO memberVO) {
		String member_id = memberVO.getMember_id();
//		String member_id = "MB00001";
		try {
			// 回傳店家
			return dao.findByMemberId(member_id);

		} catch (NullPointerException e) {
			// 店家尚未新增
			return null;
		}
	}

	public StoreVO findByMemberId(String member_id) {
		try {
			// 回傳店家
			return dao.findByMemberId(member_id);
		} catch (NullPointerException e) {
			// 店家尚未新增
			return null;
		}
	}

	public void changeStoreOn(StoreVO storeVO) {
		if (storeVO.getStore_on() == 0) {
			storeVO.setStore_on(1);
		} else {
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

	public void insertWithClosed(StoreVO storeVO, List<Store_closedVO> list) {
		dao.insertWithClosed(storeVO, list);
	}

	public Map<String, Integer> getAllCalculated() {
		Map<String, Integer> calculated = new HashMap<>();
		List<StoreVO> getall = getAll();
		int total = 0;
		int restaurant = 0;
		int hostel = 0;
		int grooming = 0;
		int school = 0;
		int hospital = 0;
		for (StoreVO s : getall) {
			total++;
			String classname = s.getStore_class();
			switch (classname) {
			case "餐廳":
				restaurant++;
				break;
			case "旅館":
				hostel++;
				break;
			case "美容":
				grooming++;
				break;
			case "學校":
				school++;
				break;
			case "醫院":
				hospital++;
				break;
			}
		}
		calculated.put("總計", total);
		calculated.put("餐廳", restaurant);
		calculated.put("旅館", hostel);
		calculated.put("美容", grooming);
		calculated.put("學校", school);
		calculated.put("醫院", hospital);
		return calculated;
	}

	public void deleteStore(String store_id) {
		dao.delete(store_id);
	}

	public StoreVO findByStoreId(String store_id) {
		return dao.findByPK(store_id);
	}

	public List<StoreVO> findByClass(String store_class) {
		return dao.findByClass(store_class);
	}
	
	public List<StoreVO> findByClass2(String store_class) {
		return dao.findByClass2(store_class);
	}

	public List<StoreVO> getAll() {
		return dao.getAll();
	}

	public static void main(String[] args) {
		StoreService ss = new StoreService();
		StoreVO sVO = new StoreVO();
//
//		sVO.setStore_id("S00100");
//		sVO.setMember_id("MB0055");
//		sVO.setStore_class("醫院");

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
//		System.out.println("新增成功");
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

		// getAllCalculated()
		Map<String, Integer> map = ss.getAllCalculated();
		Set set = map.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			System.out.print(key+"\t");
		}
		System.out.println();
		Collection val = map.values();
		for (Object c : val) {
			System.out.print(c.toString()+"\t");
		}

	}
}
