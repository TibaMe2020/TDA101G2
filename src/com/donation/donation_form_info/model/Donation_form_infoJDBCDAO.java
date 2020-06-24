package com.donation.donation_form_info.model;

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







public class Donation_form_infoJDBCDAO implements Donation_form_infoDAO_interface {
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
	
	private static final String INSERT_STMT = "INSERT INTO donation_form_info (donation_form_id,npo_id,member_id,donator_name,donation_money,payment,donator_phone_num,receipt_type) VALUES ('DID' || lpad(donation_form_id_SEQ.nextval, 5, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM donation_form_info";
//	private static final String GET_ONE_DonationForm = "SELECT * FROM Donation_form_info where donation_form_id=?";
	private static final String GET_ONE_DonationForm = "SELECT * FROM donation_form_info where donator_name = ? and donator_phone_num = ? " ;
	private static final String INSERT_UPDATE = "INSERT INTO donation_form_info (donation_form_id,npo_id,member_id,donator_name,donation_money,payment,donator_phone_num,receipt_type) VALUES ('DID' || lpad(donation_form_id_SEQ.nextval, 5, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_SELECT = "SELECT * FROM donation_form_info where donator_name = ? and donator_phone_num = ? ";
	@Override
	public void insert(Donation_form_infoVO donation_form_infoVO) {
		Connection con = null;
		PreparedStatement ppst = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(INSERT_STMT);

			ppst.setString(1, donation_form_infoVO.getNpo_id());
			ppst.setString(2, donation_form_infoVO.getMember_id());
			ppst.setString(3, donation_form_infoVO.getDonator_name());
			ppst.setInt(4, donation_form_infoVO.getDonation_money());
			ppst.setString(5, donation_form_infoVO.getPayment());
			ppst.setString(6, donation_form_infoVO.getDonator_phone_num());
			ppst.setString(7, donation_form_infoVO.getReceipt_type());



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
	public List<Donation_form_infoVO> getAll() {
		List<Donation_form_infoVO> list = new ArrayList<Donation_form_infoVO>();
		Donation_form_infoVO donation_form_infoVO = null;
		
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
				donation_form_infoVO = new Donation_form_infoVO();
				donation_form_infoVO.setDonation_form_id(rs.getString("donation_form_id"));
				donation_form_infoVO.setNpo_id(rs.getString("npo_id"));
				donation_form_infoVO.setMember_id(rs.getString("member_id"));
				donation_form_infoVO.setDonator_name(rs.getString("donator_name"));
				donation_form_infoVO.setDonation_money(rs.getInt("donation_money"));
				donation_form_infoVO.setPayment(rs.getString("payment"));
				donation_form_infoVO.setDonator_phone_num(rs.getString("donator_phone_num"));
				donation_form_infoVO.setReceipt_type(rs.getString("receipt_type"));
				donation_form_infoVO.setCreate_time(rs.getTimestamp("create_time"));

				list.add(donation_form_infoVO); // Store the row in the list
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

	@Override
	public Donation_form_infoVO findByPrimaryKey(String donator_name , String donation_phone_num) {
		Donation_form_infoVO donation_form_infoVO = null;
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_DonationForm);
			
			ppst.setString(1, donator_name);
			ppst.setString(2, donation_phone_num);

			rs = ppst.executeQuery();
			

			while (rs.next()) {
				donation_form_infoVO = new Donation_form_infoVO();
				donation_form_infoVO.setNpo_id(rs.getString("npo_id"));
				donation_form_infoVO.setDonator_name(rs.getString("donator_name"));
				donation_form_infoVO.setDonation_form_id(rs.getString("donation_form_id")); //?
				donation_form_infoVO.setCreate_time(rs.getTimestamp("create_time"));
				donation_form_infoVO.setDonation_money(rs.getInt("donation_money"));
				donation_form_infoVO.setPayment(rs.getString("payment"));
				
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
		return donation_form_infoVO;
	}
	public static void main(String[] args) {
		Donation_form_infoJDBCDAO dao = new Donation_form_infoJDBCDAO();
		
//		Donation_form_infoVO donation_form_info1 = new Donation_form_infoVO();
		
//		donation_form_info1.setNpo_id("1000000001");
//		donation_form_info1.setMember_id("MB00009");
//		donation_form_info1.setDonator_name("灰太郎");
//		donation_form_info1.setDonation_money(100);
//		donation_form_info1.setPayment("信用卡");
//		donation_form_info1.setDonator_phone_num("0976023698");
//		donation_form_info1.setReceipt_type("信用卡");
//
//		dao.insert(donation_form_info1);
		
		List<Donation_form_infoVO> list = dao.getAll();
		for (Donation_form_infoVO aDo : list) {
			System.out.print(aDo.getDonation_form_id() + ",");
			System.out.print(aDo.getNpo_id() + ",");
			System.out.print(aDo.getMember_id() + ",");
			System.out.print(aDo.getDonator_name() + ",");
			System.out.print(aDo.getDonation_money() + ",");
			System.out.print(aDo.getDonator_phone_num() + ",");
			System.out.print(aDo.getPayment()+ ",");
			System.out.print(aDo.getReceipt_type()+ ",");
			System.out.print(aDo.getCreate_time());
			System.out.println(dao.getAll());
	
//		List<Donation_form_infoVO> listt = dao.getSelect("宋仲基", "0976023691");
//		for (Donation_form_infoVO aDo : listt) {
//			System.out.print(aDo.getDonation_form_id() + ",");
//			System.out.print(aDo.getDonator_name() + ",");
//			System.out.print(aDo.getDonation_money() + ",");
//			System.out.print(aDo.getDonator_phone_num() + ",");
//			System.out.print(aDo.getPayment()+ ",");
//			System.out.print(aDo.getReceipt_type()+ ",");
//			System.out.print(aDo.getCreate_time());
	
			
		}
//		Donation_form_infoVO donation_form_infoVO3 = dao.findByPrimaryKey("宋仲基","976233145");
//		System.out.print(donation_form_infoVO3.getDonator_name() + ",");
	}





	@Override
	public void update(Donation_form_infoVO donation_form_infoVO) {
		Connection con = null;
		PreparedStatement ppst = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(INSERT_UPDATE);
			
			
			ppst.setString(1, donation_form_infoVO.getNpo_id());
			ppst.setString(2, donation_form_infoVO.getMember_id());
			ppst.setString(3, donation_form_infoVO.getDonator_name());
			ppst.setInt(4, donation_form_infoVO.getDonation_money());
			ppst.setString(5, donation_form_infoVO.getPayment());
			ppst.setString(6, donation_form_infoVO.getDonator_phone_num());
			ppst.setString(7, donation_form_infoVO.getReceipt_type());

			
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
	public List<Donation_form_infoVO> getSelect(String donator_name, String donation_phone_num) {
		List<Donation_form_infoVO> listt = new ArrayList<Donation_form_infoVO>();
		Donation_form_infoVO donation_form_infoVO = null;
		
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_SELECT);
			
			ppst.setString(1, donator_name);
			ppst.setString(2, donation_phone_num);

			rs = ppst.executeQuery();


			while (rs.next()) {
				donation_form_infoVO = new Donation_form_infoVO();
				donation_form_infoVO.setDonation_form_id(rs.getString("donation_form_id"));
				donation_form_infoVO.setNpo_id(rs.getString("npo_id"));
				donation_form_infoVO.setDonator_name(rs.getString("donator_name"));
				donation_form_infoVO.setDonation_money(rs.getInt("donation_money"));
				donation_form_infoVO.setPayment(rs.getString("payment"));
				donation_form_infoVO.setDonator_phone_num(rs.getString("donator_phone_num"));
				donation_form_infoVO.setReceipt_type(rs.getString("receipt_type"));
				donation_form_infoVO.setCreate_time(rs.getTimestamp("create_time"));

				listt.add(donation_form_infoVO); // Store the row in the list
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
		
		return listt;
	}
	 }






	

//}

