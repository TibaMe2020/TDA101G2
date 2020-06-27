package com.product.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.product_version.model.Version_VO;

public class Product_Service {

	private Product_DAO_interface dao;

	public Product_Service() {
		dao = new Product_DAO();
	}

	public void insertWithVersion(Product_VO product_VO, List<Version_VO> list) {
		dao.insertWithVersion(product_VO, list);
	}

	public Product_VO addProduct(Product_VO product_VO) {
		dao.insert(product_VO);
		return product_VO;
	}

	public Product_VO updateProduct(Product_VO product_VO) {
		dao.update(product_VO);
		return product_VO;
	}

	public void deleteProduct(String product_id) {
		dao.delete(product_id);
	}

	public Product_VO getOneProduct(String product_id) {
		return dao.findByPrimaryKey(product_id);

	}
//關鍵字
	public List<Product_VO> getbykeyword() {
		return dao.getbykeyword();
	}
//yaosheng
	public List<Product_VO> all() {
		List<Product_VO> collect = dao.all().stream()
		.filter(p -> p.getProduct_state() == 1)
		.collect(Collectors.toList());
		return collect;
	}

//最新日期
	public List<Product_VO> newDate() {
		return dao.newDate();
	}
//抓取食品最低價錢
	public List<Product_VO> lowPrice() {
		List<Product_VO> all = dao.FinalALL();
		List<Product_VO> list = new ArrayList<>();
		for(Product_VO product_VO : all) {
			String product_id = product_VO.getProduct_id();
			Optional<Product_VO> findFirst = dao.highPrice().stream()
												.filter(p -> p.getProduct_id().equals(product_id))
//												.filter(p -> p.getProduct_class().equals("food") || p.getProduct_class().equals("cloth"))
												.filter(p -> p.getProduct_class().equals("食品") || p.getProduct_class().equals("服飾"))
												.findFirst();
			if(findFirst.isPresent()) {
				list.add(findFirst.get());
			}
		}
		
		List<Product_VO> collect = list.stream().sorted(Comparator.comparing(Product_VO::getPrice))
												.collect(Collectors.toList());
		if(collect.isEmpty()) {
			return null;
		}
		
		return collect;
	}

//Yaosheng//抓取食品最高價錢
	public List<Product_VO> highPrice() {
		List<Product_VO> all = dao.FinalALL();
		List<Product_VO> list = new ArrayList<>();
		for(Product_VO product_VO : all) {
			String product_id = product_VO.getProduct_id();
			Optional<Product_VO> findFirst = dao.highPrice().stream()
												.filter(p -> p.getProduct_id().equals(product_id))
												.filter(p -> p.getProduct_class().equals("食品"))
												.findFirst();
			if(findFirst.isPresent()) {
				list.add(findFirst.get());
			}
		}
		
		List<Product_VO> collect = list.stream().sorted(Comparator.comparing(Product_VO::getPrice).reversed())
												.collect(Collectors.toList());
		if(collect.isEmpty()) {
			return null;
		}
		
		return collect;
	}

//抓取食品最高評價
	public List<Product_VO> highScore() {
		List<Product_VO> all = dao.FinalALL();
		List<Product_VO> list = new ArrayList<>();
		for(Product_VO product_VO : all) {
			String product_id = product_VO.getProduct_id();
			Optional<Product_VO> findFirst = dao.highPrice().stream()
												.filter(p -> p.getProduct_id().equals(product_id))
												.filter(p -> p.getProduct_class().equals("食品"))
												.findFirst();
			if(findFirst.isPresent()) {
				list.add(findFirst.get());
			}
		}
		
		List<Product_VO> collect = list.stream().sorted(Comparator.comparing(Product_VO::getScore).reversed())
												.collect(Collectors.toList());
		if(collect.isEmpty()) {
			return null;
		}
		
		return collect;
	}

	// 萬用查詢
	public List<Product_VO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public List<Product_VO> getByMId(String member_id) {
		return dao.getByMId(member_id);
	}

	public Map<String, String> getByClass() {
		return dao.getByClass();
	}


}
