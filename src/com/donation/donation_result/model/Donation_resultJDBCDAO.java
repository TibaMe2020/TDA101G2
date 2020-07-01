package com.donation.donation_result.model;

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

import com.donation.donation_result.model.Donation_resultVO;
import com.donation.npo_info.model.Npo_infoVO;



public class Donation_resultJDBCDAO implements Donation_resultDAO_interface {
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
	private static final String INSERT_STMT ="INSERT INTO Donation_result (result_id, npo_id, member_id, result_month, result_image,result_content) VALUES ('RID' || lpad(result_id_SEQ.nextval, 5, '0'), ?, ?, ?, ?, ?)";//,result_month
//	private static final String GET_ALL_STMT ="SELECT result_id, npo_id, member_id, result_month, result_image, result_content FROM Donation_result"; //, result_month
	private static final String GET_ALL_STMT ="SELECT result_id, npo_id, member_id, result_month, result_content,  create_time, update_time FROM Donation_result"; //, result_month
	private static final String GET_ONE_Result = "SELECT * FROM donation_result where result_id = ?";
	private static final String GET_ONE_Month = "SELECT * FROM donation_result where result_month = ?";
	private static final String DELETE_Donation_result = "DELETE FROM Donation_result where result_id = ?";


	@Override
	public void insert(Donation_resultVO donation_resultVO) {
		
		Connection con = null;
		PreparedStatement ppst = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(INSERT_STMT);

			ppst.setString(1, donation_resultVO.getNpo_id());
			ppst.setString(2, donation_resultVO.getMember_id());
			ppst.setInt(3, donation_resultVO.getResult_month());
			ppst.setBytes(4, donation_resultVO.getResult_image());
			ppst.setString(5, donation_resultVO.getResult_content());

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



//	@Override
//	public void update(Donation_resultVO donation_resultVO) {
//		// TODO Auto-generated method stub
//		
//	}



	@Override
	public void delete(String result_id) {
		
		Connection con = null;
		PreparedStatement ppst = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			con.setAutoCommit(false);

			// 先刪除員工
			ppst = con.prepareStatement(DELETE_Donation_result);
			ppst.setString(1, result_id);
		    ppst.executeUpdate();
//			updateCount_ = ppst.executeUpdate();
		

			con.commit();
			con.setAutoCommit(true);
			System.out.println("DELETE YES");

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
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
	public Donation_resultVO findByPrimaryKey(String result_id) {
		
		Donation_resultVO donation_resultVO = null;
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_Result);
			
			ppst.setString(1, result_id);
			
			rs = ppst.executeQuery();
			
			while (rs.next()) {
				donation_resultVO = new Donation_resultVO();
				donation_resultVO.setResult_id(rs.getString("result_id")); //?
				donation_resultVO.setResult_month(rs.getInt("result_month"));
				donation_resultVO.setMember_id(rs.getString("member_id"));
				donation_resultVO.setNpo_id(rs.getString("npo_id"));
				donation_resultVO.setResult_image(rs.getBytes("result_image"));
				donation_resultVO.setResult_content(rs.getString("result_content"));
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
		return donation_resultVO;
	}



	@Override
	public List<Donation_resultVO> getAll() {
		List<Donation_resultVO> list = new ArrayList<Donation_resultVO>();
		Donation_resultVO donation_resultVO = null;
		
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ALL_STMT);
			rs = ppst.executeQuery();

			while (rs.next()) {
				donation_resultVO = new Donation_resultVO();
				donation_resultVO.setResult_id(rs.getString("result_id"));
				donation_resultVO.setNpo_id(rs.getString("npo_id"));
				donation_resultVO.setMember_id(rs.getString("member_id"));
				donation_resultVO.setResult_month(Integer.valueOf(rs.getString("result_month")));
//				donation_resultVO.setResult_image(rs.getBytes("result_image"));
				donation_resultVO.setResult_content(rs.getString("result_content"));
				donation_resultVO.setCreate_time(rs.getTimestamp("create_time"));
				donation_resultVO.setUpdate_time(rs.getTimestamp("update_time"));

				list.add(donation_resultVO); // Store the row in the list
			}

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	

	public static void main(String[] args) {
		Donation_resultJDBCDAO dao = new Donation_resultJDBCDAO();
		
		//新增
//		Donation_resultVO donation_resultVO1 = new Donation_resultVO();
//		donation_resultVO1.setNpo_id("1000000002");
//		donation_resultVO1.setMember_id("MB00003");
//		donation_resultVO1.setResult_image(null);
//		donation_resultVO1.setResult_content("今天早");
//		dao.insert(donation_resultVO1);
//		
		
//		dao.delete("1000000002");
		
//		Donation_resultVO donation_resultVO3 = dao.findByMonth(2);
//		System.out.print(donation_resultVO3.getResult_content() + "," + donation_resultVO3.getNpo_id());

		//查看全部
		List<Donation_resultVO> list = dao.getMonth(4);
		for (Donation_resultVO aDr : list) {
			System.out.print(aDr.getResult_id() + ",");
			System.out.print(aDr.getMember_id() + ",");
			System.out.print(aDr.getNpo_id() + ",");
			System.out.println(aDr.getResult_month() + "!");
			System.out.print(aDr.getResult_image() + ",");
			System.out.print(aDr.getResult_content()+ ",");
			System.out.print(aDr.getCreate_time()+",");
			System.out.print(aDr.getUpdate_time());

			System.out.println();
		}
	
	




	}



	@Override
	public List<Donation_resultVO> getMonth(Integer result_month) {
		List<Donation_resultVO> list = new ArrayList<Donation_resultVO>();
		
		Donation_resultVO donation_resultVO = null;		
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		
		try {


//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_Month);
			
			ppst.setInt(1, result_month);

			rs = ppst.executeQuery();

			while (rs.next()) {
				donation_resultVO = new Donation_resultVO();
				donation_resultVO.setResult_id(rs.getString("result_id"));
				donation_resultVO.setNpo_id(rs.getString("npo_id"));
				donation_resultVO.setMember_id(rs.getString("member_id"));
				donation_resultVO.setResult_month(Integer.valueOf(rs.getString("result_month")));
				donation_resultVO.setResult_image(rs.getBytes("result_image"));
				donation_resultVO.setResult_content(rs.getString("result_content"));
				donation_resultVO.setCreate_time(rs.getTimestamp("create_time"));
				donation_resultVO.setUpdate_time(rs.getTimestamp("update_time"));

				list.add(donation_resultVO); // Store the row in the list
			}

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}






	
}