package com.product_order_detail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Order_detail_DAO implements Order_detail_DAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "PETBOX";
//	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO product_order_detail (product_order_detail_id,product_order_id, product_version_id,quantity) VALUES(PODI_SEQ.NEXTVAL,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT product_order_detail_id, product_order_id, product_version_id, quantity from product_order_detail WHERE product_order_detail_id=?";
	private static final String GET_ALL_STMT = "SELECT product_order_detail_id, product_order_id, product_version_id, quantity from product_order_detail ORDER BY  product_order_detail_id";;
	private static final String GET_BY_OMID_STMT = "select * from product_order_detail " + 
			"where product_order_id = ?";
	
	public List<Order_detail_VO> getByOmId(String omId) {
		List<Order_detail_VO> list = new ArrayList<Order_detail_VO>();
		Order_detail_VO order_detail_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_OMID_STMT);
			pstmt.setString(1, omId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detail_VO = new Order_detail_VO();
				order_detail_VO.setProduct_order_detail_id(rs.getString("product_order_detail_id"));
				order_detail_VO.setProduct_order_id(rs.getString("product_order_id"));
				order_detail_VO.setProduct_version_id(rs.getString("product_version_id"));
				order_detail_VO.setQuantity(rs.getInt("quantity"));
				list.add(order_detail_VO);
			}
			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public void insert(Order_detail_VO order_detail_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_detail_VO.getProduct_order_detail_id());
			pstmt.setString(2, order_detail_VO.getProduct_order_id());
			pstmt.setInt(3, order_detail_VO.getQuantity());
			pstmt.executeUpdate();

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Order_detail_VO findByPrimaryKey(String product_order_detail_id) {
		Order_detail_VO order_detail_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_order_detail_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detail_VO = new Order_detail_VO();
				order_detail_VO.setProduct_order_detail_id(rs.getString("product_order_detail_id"));
				order_detail_VO.setProduct_order_id(rs.getString("product_order_id"));
				order_detail_VO.setProduct_version_id(rs.getString("product_version_id"));
				order_detail_VO.setQuantity(rs.getInt("quantity"));

			}

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return order_detail_VO;
	}

	@Override
	public List<Order_detail_VO> getAll() {
		List<Order_detail_VO> list = new ArrayList<Order_detail_VO>();
		Order_detail_VO order_detail_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detail_VO = new Order_detail_VO();
				order_detail_VO.setProduct_order_detail_id(rs.getString("product_order_detail_id"));
				order_detail_VO.setProduct_order_id(rs.getString("product_order_id"));
				order_detail_VO.setProduct_version_id(rs.getString("product_version_id"));
				order_detail_VO.setQuantity(rs.getInt("quantity"));
				list.add(order_detail_VO);

			}

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public void insert2(Order_detail_VO order_detail_VO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setString(1, order_detail_VO.getProduct_order_id());
			pstmt.setString(2, order_detail_VO.getProduct_version_id());
			pstmt.setInt(3, order_detail_VO.getQuantity());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-detail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	

	public static void main(String[] args) {
		Order_detail_DAO dao = new Order_detail_DAO();

		// insert
//		Order_detail_VO insert = new Order_detail_VO();
//		insert.setOrder_id("POI00010");
//		insert.setVersion_id("PVI00007");
//		insert.setQuantity(3);
//		dao.insert(insert);
//		System.out.println(insert);

		// SEARCH(ONE)

//		Order_detail_VO order_detail_search = dao.findByPrimaryKey("PODI00001");
//		System.out.println(
//				order_detail_search.getProduct_order_detail_id() + "," + order_detail_search.getProduct_order_id() + ","
//						+ order_detail_search.getProduct_version_id() + "," + order_detail_search.getQuantity());

		// SEARCH(MORE)
		List<Order_detail_VO> list = dao.getAll();
		for (Order_detail_VO all : list) {
			System.out.println(all.getProduct_order_detail_id() + "," + all.getProduct_order_id() + ","
					+ all.getProduct_version_id() + "," + all.getQuantity());

		}
	}

	

	
}
