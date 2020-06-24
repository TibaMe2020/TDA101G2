package com.donation.adopt_form_info.model;

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


public class Adopt_form_infoJDBCDAO implements Adopt_form_infoDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO Adopt_form_info (adopt_form_id,adopt_id,adopt_talk,member_id,adopt_person,payadopt_person,adopt_phone_num,adopt_payment, adopt_certificate, adopt_email, address) VALUES ('AFD' || lpad(adopt_form_id_SEQ.nextval, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adopt_form_id ,adopt_id, member_id, adopt_person , adopt_talk , payadopt_person , adopt_phone_num , adopt_payment ,adopt_certificate, adopt_email , address, create_time FROM Adopt_form_info";
    private static final String GET_ONE_ADOPTFORM = "SELECT * FROM adopt_form_info where adopt_form_id = ?";
	


	@Override
	public void insert(Adopt_form_infoVO adopt_form_infoVO) {
		Connection con = null;
		PreparedStatement ppst = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(INSERT_STMT);


//			ppst.setString(1, adopt_form_infoVO.getAdopt_from_id());
			ppst.setString(1, adopt_form_infoVO.getAdopt_id());
			ppst.setString(2, adopt_form_infoVO.getAdopt_talk());
			ppst.setString(3, adopt_form_infoVO.getMember_id());
			ppst.setString(4, adopt_form_infoVO.getAdopt_person());
			ppst.setString(5, adopt_form_infoVO.getPayadopt_person());
			ppst.setString(6, adopt_form_infoVO.getAdopt_phone_num());
			ppst.setString(7, adopt_form_infoVO.getAdopt_payment());
			ppst.setString(8, adopt_form_infoVO.getAdopt_certificate());
			ppst.setString(9, adopt_form_infoVO.getAdopt_email());
			ppst.setString(10, adopt_form_infoVO.getAddress());
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
	public List<Adopt_form_infoVO> getAll() {
		List<Adopt_form_infoVO> list = new ArrayList<Adopt_form_infoVO>();
		Adopt_form_infoVO adopt_form_infoVO = null;
		
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
				adopt_form_infoVO = new Adopt_form_infoVO();
				adopt_form_infoVO.setAdopt_form_id(rs.getString("adopt_form_id"));
				adopt_form_infoVO.setAdopt_id(rs.getString("adopt_id"));
				adopt_form_infoVO.setMember_id(rs.getString("member_id"));
				adopt_form_infoVO.setAdopt_person(rs.getString("adopt_person"));
				adopt_form_infoVO.setAdopt_talk(rs.getString("adopt_talk"));
				adopt_form_infoVO.setPayadopt_person(rs.getString("payadopt_person"));
				adopt_form_infoVO.setAdopt_phone_num(rs.getString("adopt_phone_num"));
				adopt_form_infoVO.setAdopt_payment(rs.getString("adopt_payment"));
				adopt_form_infoVO.setAdopt_certificate(rs.getString("adopt_certificate"));
				adopt_form_infoVO.setAdopt_email(rs.getString("adopt_email"));
				adopt_form_infoVO.setAddress(rs.getString("address"));
				adopt_form_infoVO.setCreate_time(rs.getTimestamp("create_time"));
	
				list.add(adopt_form_infoVO); // Store the row in the list
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
		Adopt_form_infoJDBCDAO dao = new Adopt_form_infoJDBCDAO();
		
//新增		
//		Adopt_form_infoVO adopt_form_infoVO1 = new Adopt_form_infoVO();
//		adopt_form_infoVO1.setAdopt_id("AID00009");
//		adopt_form_infoVO1.setMember_id("MB00003");
//		adopt_form_infoVO1.setAdopt_person("王美美");
//		adopt_form_infoVO1.setAdopt_talk("保重");
//		adopt_form_infoVO1.setPayadopt_person("沙拉");
//		adopt_form_infoVO1.setAdopt_phone_num("0976023698");
//		adopt_form_infoVO1.setAdopt_payment("信用卡");
//		adopt_form_infoVO1.setAdopt_certificate("2");
//		adopt_form_infoVO1.setAdopt_email("sleppea@yahoo.com");
//		adopt_form_infoVO1.setAddress("台北市中山南路一段一號一巷五之一");
//		dao.insert(adopt_form_infoVO1);
//		System.out.println();

		
		//刪除	
//		dao.delete("3000000012");
//		
		List<Adopt_form_infoVO> list = dao.getAll();
		for (Adopt_form_infoVO aFi : list) {
			System.out.print(aFi.getAdopt_form_id() + ",");
			System.out.print(aFi.getAdopt_id() + ",");
			System.out.print(aFi.getMember_id() + ",");
			System.out.print(aFi.getAdopt_person() + ",");
			System.out.print(aFi.getAdopt_talk());
			System.out.print(aFi.getAdopt_phone_num());
			System.out.print(aFi.getAdopt_payment());
			System.out.print(aFi.getAdopt_email());
			System.out.print(aFi.getAddress());


			System.out.println();
		}
	}




	@Override
	public Adopt_form_infoVO findByPrimaryKey(String adopt_form_id) {
		
		Adopt_form_infoVO adopt_form_infoVO = null;
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,userid,passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_ADOPTFORM);
			
			ppst.setString(1, adopt_form_id);
			rs = ppst.executeQuery();while (rs.next()) {
				
				adopt_form_infoVO = new Adopt_form_infoVO();
				adopt_form_infoVO.setAdopt_form_id(rs.getString("adopt_id")); //?
				adopt_form_infoVO.setAdopt_person(rs.getString("adopt_person"));
				adopt_form_infoVO.setPayadopt_person(rs.getString("payadopt_person"));
				adopt_form_infoVO.setAdopt_payment(rs.getString("adopt_payment"));
				adopt_form_infoVO.setAdopt_certificate(rs.getString("adopt_certificate"));
				adopt_form_infoVO.setAddress(rs.getString("Address"));
				adopt_form_infoVO.setAdopt_email(rs.getString("Adopt_email"));
				
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
		return adopt_form_infoVO;
	}
}
