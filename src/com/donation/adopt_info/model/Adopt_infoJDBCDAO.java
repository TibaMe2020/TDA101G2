package com.donation.adopt_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Adopt_infoJDBCDAO implements Adopt_infoDAO_interface {
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "PETBOX";
//	String passwd = "123456";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_Adopt = "INSERT INTO adopt_info (adopt_id,adopt_image,adopt_name,adopt_description,adopt_money) VALUES ('AID' || lpad(adopt_id_SEQ.nextval, 5, '0'), ?, ?, ?,?)";
	private static final String GET_ALL_Adopt = "SELECT adopt_id ,adopt_image,  adopt_name, adopt_description, adopt_money, create_time, update_time FROM Adopt_info";
	private static final String GET_ONE_Adopt = "SELECT adopt_id , adopt_image, adopt_name, adopt_description, adopt_money, create_time, update_time FROM Adopt_info where adopt_id=?";
	private static final String DELETE_ADOPT = "DELETE FROM Adopt_info where adopt_id = ?";	
	private static final String UPDATE = "UPDATE Adopt_info SET adopt_image=?, adopt_name=?, adopt_description=? where adopt_id=?";
	

	@Override
	public void insert(Adopt_infoVO adopt_infoVO) {
		Connection con = null;
		PreparedStatement ppst = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(INSERT_Adopt);

//			ppst.setString(1, adopt_infoVO.getAdopt_id());
			ppst.setBytes(1, adopt_infoVO.getAdopt_image());
			ppst.setString(2, adopt_infoVO.getAdopt_name());
			ppst.setString(3, adopt_infoVO.getAdopt_description());
			ppst.setInt(4, adopt_infoVO.getAdopt_money()); //不能null

			ppst.executeUpdate();

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ppst != null) {
				try {
					ppst.close();
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
	public void update(Adopt_infoVO adopt_infoVO) {
		Connection con = null;
		PreparedStatement ppst = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(UPDATE);
			
			ppst.setBytes(1, adopt_infoVO.getAdopt_image());
			ppst.setString(2, adopt_infoVO.getAdopt_name());
			ppst.setString(3, adopt_infoVO.getAdopt_description());
//			ppst.setInt(4, adopt_infoVO.getAdopt_money());
			ppst.setString(4, adopt_infoVO.getAdopt_id());
			
			ppst.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ppst != null) {
				try {
					ppst.close();
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
	public void delete(String adopt_id) {
		Connection con = null;
		PreparedStatement ppst = null;
	try {
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
		con = ds.getConnection();
		con.setAutoCommit(false);


	ppst = con.prepareStatement(DELETE_ADOPT);
	ppst.setString(1, adopt_id);
	ppst.executeUpdate();
	
	con.commit();
	con.setAutoCommit(true);
	System.out.println("DELETE YES");
	
//	}catch (ClassNotFoundException e) {
//		throw new RuntimeException("Couldn't load database driver. "
//				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		if (con != null) {
			try {
				// 3●設定於當有exception發生時之catch區塊內
				con.rollback();
			} catch (SQLException excep) {
				throw new RuntimeException("rollback error occured. "
						+ excep.getMessage());
			}
		}
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	} finally {
		if (ppst != null) {
			try {
				ppst.close();
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
	public Adopt_infoVO findByPrimaryKey(String adopt_id) {
		
		Adopt_infoVO adopt_infoVO = null;
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_Adopt);
			
			ppst.setString(1, adopt_id);
			
			rs = ppst.executeQuery();
			
			while (rs.next()) {
				adopt_infoVO = new Adopt_infoVO();
				adopt_infoVO.setAdopt_id(rs.getString("adopt_id")); //?
				adopt_infoVO.setAdopt_image(rs.getBytes("adopt_image"));
				adopt_infoVO.setAdopt_name(rs.getString("adopt_name"));
				adopt_infoVO.setAdopt_description(rs.getString("adopt_description"));
				adopt_infoVO.setAdopt_money(rs.getInt("adopt_money"));
				adopt_infoVO.setCreate_time(rs.getTimestamp("create_time"));
				adopt_infoVO.setUpdate_time(rs.getTimestamp("update_time"));
				
			}
//		 }catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (ppst != null) {
					try {
						ppst.close();
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
		return adopt_infoVO;
	}

	@Override
	public List<Adopt_infoVO> getAll() {
		List<Adopt_infoVO> list = new ArrayList<Adopt_infoVO>();
		Adopt_infoVO adopt_infoVO = null;
		
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareCall(GET_ALL_Adopt);
			rs = ppst.executeQuery();
			
			while(rs.next()) {
				adopt_infoVO = new Adopt_infoVO();
				adopt_infoVO.setAdopt_id(rs.getString("adopt_id"));
				adopt_infoVO.setAdopt_image(rs.getBytes("adopt_image"));
				adopt_infoVO.setAdopt_name(rs.getString("adopt_name"));
				adopt_infoVO.setAdopt_description(rs.getString("adopt_description"));
				adopt_infoVO.setAdopt_money(rs.getInt("adopt_money"));
				adopt_infoVO.setCreate_time(rs.getTimestamp("create_time"));
				adopt_infoVO.setUpdate_time(rs.getTimestamp("update_time"));

				list.add(adopt_infoVO);
			}
//		}catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
				// Handle any SQL errors
		} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
		}finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (ppst != null) {
					try {
						ppst.close();
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
		Adopt_infoJDBCDAO dao = new Adopt_infoJDBCDAO();
		
//		Adopt_infoVO adopt_infoVO1 = new Adopt_infoVO();
//		adopt_infoVO1.setAdopt_image(null);
//		adopt_infoVO1.setAdopt_name("王小名");
//		adopt_infoVO1.setAdopt_description("我是一隻可愛的狗");
//		adopt_infoVO1.setAdopt_money(510);
//
//		dao.insert(adopt_infoVO1);
//		System.out.println("INSERT 成功");
		
//		dao.delete("123456");
		
		
//		Adopt_infoVO adopt_infoVO2 = new Adopt_infoVO();
//		adopt_infoVO2.setAdopt_image(null);
//		adopt_infoVO2.setAdopt_name("吳1");
//		adopt_infoVO2.setAdopt_description("安安您好");
//		adopt_infoVO2.setAdopt_money(900);
//		adopt_infoVO2.setAdopt_id("AID00011");
//		dao.update(adopt_infoVO2);
//		System.out.println("更新成功");
		
//		Adopt_infoVO adopt_infoVO3 = dao.findByPrimaryKey("AID00001");
//		System.out.print(adopt_infoVO3.getAdopt_image() + ",");
		
		List<Adopt_infoVO> list = dao.getAll();
		for (Adopt_infoVO aDo : list) {
			System.out.print(aDo.getAdopt_id() + ",");
			System.out.print(aDo.getAdopt_image() + ",");
			System.out.print(aDo.getAdopt_name() + ",");
			System.out.print(aDo.getAdopt_description() + ",");
			System.out.print(aDo.getAdopt_money()+ ",");
			System.out.print(aDo.getCreate_time()+ ",");
			System.out.print(aDo.getUpdate_time());
//			System.out.println(dao.getAll());
		}
	}
}
