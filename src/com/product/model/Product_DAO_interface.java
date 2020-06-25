package com.product.model;

import java.util.List;
import java.util.Map;

import com.product_version.model.Version_VO;

public interface Product_DAO_interface {

	public void insert(Product_VO product_VO);

	public void update(Product_VO product_VO);

	public void delete(String Product_id);

	public Product_VO findByPrimaryKey(String Product_id);

//最新日期
	public List<Product_VO> newDate();

//萬用查詢
	public List<Product_VO> getAll(Map<String, String[]> map);

//價錢最高
	public List<Product_VO> highPrice();

//價錢最低
	public List<Product_VO> lowPrice();

//最高評分
	public List<Product_VO> highScore();

//查單一會員購買的訂單
	public List<Product_VO> getByMId(String member_id);

//同時增加產品跟規格
	public void insertWithVersion(Product_VO product_VO, List<Version_VO> list);

	public Map<String, String> getByClass();

//全部查詢最新日期
	public List<Product_VO> all();

//關鍵字
	public List<Product_VO> getbykeyword();

//拿全部商品
	public List<Product_VO> FinalALL();

}
