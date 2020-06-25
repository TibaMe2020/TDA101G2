package com.product.model;

import java.util.List;
import java.util.Map;

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
// yaosheng
	public List<Product_VO> all() {
		return dao.all();
	}

//最新日期
	public List<Product_VO> getAll() {
		return dao.getAll();
	}

	public List<Product_VO> lowPrice() {
		return dao.lowPrice();
	}

	public List<Product_VO> highPrice() {
		return dao.highPrice();
	}

	public List<Product_VO> highScore() {
		return dao.highScore();
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
	//食品最新上架
	public List<Product_VO> getbyNewdatefood() {
		return dao.getbyNewdatefood();
	}
	//食品最低價錢
	public List<Product_VO> getbylowPricefood() {
		return dao.getbylowPricefood();
	}
	//食品最高價錢
	public List<Product_VO> getbyhighPricefood() {
		return dao.getbyhighPricefood();
	}
	//食品最高評分
	public List<Product_VO> getbyhighscorefood() {
		return dao.getbyhighscorefood();
	}

}
