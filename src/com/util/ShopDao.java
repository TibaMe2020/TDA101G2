package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ShopDao {
	
	private static DataSource ds;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ShopDao dao = new ShopDao();
//		List<SellerOrderVO> list = dao.getSellerOrder("MB00001");
		
		
		List<ProductDetailVO> list1 = dao.getProductDetail("POI00005");
//		for(ProductDetailVO p : list1) System.out.println(p);
		
		
	}
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PETBOX";
	String passwd = "123456";
	
	private static final String GETORDERS = "select DISTINCT p.member_id seller_id, pom.member_id buyer_id, pom.product_order_id, "
			+ "pom.product_order_state, pom.create_time, sum(price*quantity) as total from product p " + 
			"join product_version pv on p.product_id = pv.product_id " + 
			"join product_order_detail pod on pv.product_version_id = pod.product_version_id " + 
			"join product_order_master pom on pod.product_order_id = pom.product_order_id " + 
			"where p.member_id = ? " + 
			"group by p.member_id, pom.member_id, pom.product_order_id, pom.create_time, pom.product_order_state " + 
			"order by pom.create_time desc";
	
	private static final String GETDETAILS = "select pom.product_order_id, name product_name ,pv.version_name,price, quantity from product p " + 
			"join product_version pv on p.product_id = pv.product_id " + 
			"join product_order_detail pod on pv.product_version_id = pod.product_version_id " + 
			"join product_order_master pom on pod.product_order_id = pom.product_order_id " + 
			"where pom.product_order_id = ?";
	
	private static final String GETALL = "";
	
	public List<SellerOrderVO> getSellerOrder(String member_id) {
			try (
//					Connection con = DriverManager.getConnection(url, userid, passwd);
					//以下為DataSource版
					Connection con = ds.getConnection(); 
					PreparedStatement ps = con.prepareStatement(GETORDERS);) {
				List<SellerOrderVO> orders = new ArrayList<>();
				SellerOrderVO order = null;
				
				ps.setString(1, member_id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					order = new SellerOrderVO();
					order.setSeller_id(rs.getString("seller_id"));
					order.setBuyer_id(rs.getString("buyer_id"));
					order.setProduct_order_id(rs.getString("product_order_id"));
					order.setProduct_order_state(rs.getInt("product_order_state"));
					order.setCreate_time(rs.getTimestamp("create_time"));
					order.setTotal(rs.getInt("total"));
					orders.add(order);
				}
				
//				for(SellerOrderVO s : orders) System.out.println(s);

				
				return orders;
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}
	
	public List<ProductDetailVO> getProductDetail(String product_order_id) {
		try (
				//DataSource版
				Connection con = ds.getConnection();
//				Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GETDETAILS);) {
			List<ProductDetailVO> list = new ArrayList<>();
			ProductDetailVO detail = null;
			
			ps.setString(1, product_order_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				detail = new ProductDetailVO();
				detail.setProduct_order_id(rs.getString("product_order_id"));
				detail.setProduct_name(rs.getString("product_name"));
				detail.setVersion_name(rs.getString("version_name"));
				detail.setPrice(rs.getInt("price"));
				detail.setQuantity(rs.getInt("quantity"));
				list.add(detail);
			}
//			for(ProductDetailVO p : list) System.out.println(p);
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	


}
