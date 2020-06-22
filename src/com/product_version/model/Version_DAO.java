package com.product_version.model;

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

public class Version_DAO implements Version_DAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO product_version(product_id,version_name,price,inventory) VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE product_version SET version_name=?, price=?, inventory=? WHERE product_version_id=?";
	private static final String DELETE = "DELETE FROM product_version WHERE product_version_id=? ";
	private static final String GET_ONE_STMT = "SELECT product_version_id, product_id, version_name, price, inventory FROM product_version WHERE product_version_id=?";
	private static final String GET_ALL_STMT = "SELECT product_version_id, product_id, version_name, price, inventory FROM product_version order by product_version_id";
	private static final String GETBYPRODUCTID="select * FROM PRODUCT_version where product_id=?";
	
	
	
	@Override
	public void insert(Version_VO version_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, version_VO.getProduct_id());
			pstmt.setString(2, version_VO.getVersion_name());
			pstmt.setInt(3, version_VO.getPrice());
			pstmt.setInt(4, version_VO.getInventory());
			pstmt.executeUpdate();

		
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
			se.printStackTrace();
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
	public void update(Version_VO version_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, version_VO.getVersion_name());
			pstmt.setInt(2, version_VO.getPrice());
			pstmt.setInt(3, version_VO.getInventory());
			pstmt.setString(4, version_VO.getProduct_id());

			pstmt.executeUpdate();

			
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
	public void delete(String Product_version_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Product_version_id);

			pstmt.executeUpdate();

		
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
	public Version_VO findByPrimaryKey(String Product_id) {

		Version_VO version_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Product_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
//				SELECT product_version_id, product_id, version_name, price, inventory FROM product_version WHERE product_version_id=?
				version_VO = new Version_VO();
				version_VO.setProduct_version_id(rs.getString("product_version_id"));
				version_VO.setProduct_id(rs.getString("product_id"));
				version_VO.setVersion_name(rs.getString("version_name"));
				version_VO.setPrice(rs.getInt("price"));
				version_VO.setInventory(rs.getInt("inventory"));
			}

			
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
		return version_VO;

	}

	@Override
	public List<Version_VO> getAll() {
		List<Version_VO> list = new ArrayList<Version_VO>();
		Version_VO version_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
//				SELECT product_version_id, product_id, version_name, price, inventory FROM product_version order by product_version_id
				version_VO = new Version_VO();
				version_VO.setProduct_version_id(rs.getString("product_version_id"));
				version_VO.setProduct_id(rs.getString("product_id"));
				version_VO.setVersion_name(rs.getString("version_name"));
				version_VO.setPrice(rs.getInt("price"));
				version_VO.setInventory(rs.getInt("inventory"));
				list.add(version_VO); // Store the row in the list
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
	public void insert2(Version_VO version_VO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     	pstmt = con.prepareStatement(INSERT_STMT);
//     		INSERT INTO product_version(product_version_id,product_id,version_name,price,inventory) VALUES(PVI_SEQ.NEXTVAL,?,?,?,?)
     	pstmt.setString(1, version_VO.getProduct_id());
			pstmt.setString(2, version_VO.getVersion_name());
			pstmt.setInt(3, version_VO.getPrice());
			pstmt.setInt(4, version_VO.getInventory());
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
	
	@Override
	public List<Version_VO> getbyProductID(String product_id) {
		List<Version_VO> list = new ArrayList<Version_VO>();
		Version_VO version_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYPRODUCTID);
			pstmt.setString(1, product_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				SELECT product_version_id, product_id, version_name, price, inventory FROM product_version order by product_version_id
				version_VO = new Version_VO();
				version_VO.setPrice(rs.getInt("price"));
				version_VO.setInventory(rs.getInt("inventory"));
				version_VO.setVersion_name(rs.getString("version_name"));
				version_VO.setProduct_version_id(rs.getString("product_version_id"));
				version_VO.setProduct_id(rs.getString("product_id"));
//				version_VO.setProduct_id(rs.getString("product_id"));	
				list.add(version_VO); // Store the row in the list
			}
			// Handle any driver errors
		
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	

	public static void main(String[] args) {
		Version_DAO dao = new Version_DAO();
		// INSERT INTO
//		Version_VO version_VOAdd = new Version_VO();
//		version_VOAdd.setProduct_id("PI00001");
//		version_VOAdd.setVersion_name("黑色款");
//		version_VOAdd.setPrice(2500);
//		version_VOAdd.setInventory(12);
//		dao.insert(version_VOAdd);
//		System.out.println(version_VOAdd);

		// UPDATE
//		Version_VO version_VOUpdate = new Version_VO();
//		version_VOUpdate.setProduct_id("PVI00010");
//		version_VOUpdate.setVersion_name("測試");
//		version_VOUpdate.setPrice(1280);
//		version_VOUpdate.setInventory(11);
//		dao.update(version_VOUpdate);
//		System.out.println(version_VOUpdate);

		// DELETE
//		dao.delete("PVI00017");

		// SEARCH(ONE)
//		Version_VO version_VOSelect = dao.findByPrimaryKey("PVI00007");
//		System.out.println(version_VOSelect.getProduct_version_id() + "," + version_VOSelect.getProduct_id() + "," + version_VOSelect.getVersion_name() + ","
//				+ version_VOSelect.getPrice() + "," + version_VOSelect.getInventory() + ",");
//		System.out.println("===============================");
		

		// SEARCH(MORE)
//		List<Version_VO> list = dao.getAll();
//		for (Version_VO all : list) {
//			System.out.println(all.getProduct_version_id() + "," + all.getProduct_id() + "," + all.getVersion_name()
//					+ "," + all.getPrice() + "," + all.getInventory() + ",");
//
//		}

	}

	

	

	


}
