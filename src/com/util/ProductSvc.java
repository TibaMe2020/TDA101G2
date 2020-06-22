package com.util;

import java.util.List;

public class ProductSvc {
	private ShopDao dao;

	public ProductSvc() {
		dao = new ShopDao();
	}
	
	public static void main(String[] args) {
		ProductSvc PSvc	= new ProductSvc();
		List<SellerOrderVO> list = PSvc.getOrders("MB00001");
		System.out.println("sellerOrder size : " +list.size());
		for(SellerOrderVO s : list) {
//			System.out.println(s);
			for(ProductDetailVO pd :s.getProduct_list()) {
				System.out.println(pd);
//				System.out.println(pd.getProduct_name());
			}
			System.out.println("========================");
		}
	}
	
	public List<SellerOrderVO> getOrders(String member_id) {
		List<SellerOrderVO> list = dao.getSellerOrder(member_id);
		for(SellerOrderVO s : list) {
			s.setProduct_list(dao.getProductDetail(s.getProduct_order_id()));
		}
		return list;
	}

}
