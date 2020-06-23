package com.donation.npo_info.model;

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

import com.donation.npo_info.model.Npo_infoVO;




public class Npo_infoJDBCDAO implements Npo_infoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PETBOX";
	String passwd ="123456";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_Npo = "INSERT INTO Npo_info(npo_id,npo_image,npo_name,npo_description) VALUES ('NID' || lpad(npo_id_SEQ.nextval, 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_Npo = "SELECT npo_id , npo_name , npo_image , npo_description ,create_time FROM Npo_info";
	private static final String GET_ONE_Npo = "SELECT npo_id , npo_name , npo_image , npo_description FROM Npo_info where npo_id = ?";
	private static final String GET_ONE_Money = "select SUM (donation_money) as total,d.npo_id From donation_form_info d join npo_info n on d.npo_id = n.npo_id where n.npo_id= ? group by d.npo_id";
	
	private static final String DELETE_Npo = "DELETE FROM Npo_info where npo_id = ?";
	
	private static final String UPDATE = "UPDATE Npo_info set npo_name=?, npo_image=?, npo_description=?  where npo_id = ?";

	@Override
	public void insert(Npo_infoVO npo_infoVO) {
		
		Connection con = null;			
		PreparedStatement ppst = null;
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,userid,passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(INSERT_Npo);
			
			ppst.setBytes(1, npo_infoVO.getNpo_image());
			ppst.setString(2, npo_infoVO.getNpo_name());
			ppst.setString(3, npo_infoVO.getNpo_description());
			
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
			if(ppst != null) {
				try{
					ppst.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
			   try {
				   con.close();
			   } catch (Exception e ) {
				   e.printStackTrace(System.err);
			   }
		    }
		}
	}

	@Override
	public void update(Npo_infoVO npo_infoVO) {
		Connection con = null;
		PreparedStatement ppst = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(UPDATE);
			
			ppst.setString(1, npo_infoVO.getNpo_name());
			ppst.setBytes(2, npo_infoVO.getNpo_image());
			ppst.setString(3, npo_infoVO.getNpo_description());
			ppst.setString(4, npo_infoVO.getNpo_id());
			
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
	public void delete(String npo_id) {
//		int updateCount_XX =0; //?
		Connection con = null;
		PreparedStatement ppst = null;
	try {
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
		con = ds.getConnection();
		con.setAutoCommit(false);

	ppst = con.prepareStatement(DELETE_Npo);
	ppst.setString(1,npo_id);
//	updateCount_XX = ppst.executeUpdate();
	
	ppst = con.prepareStatement(DELETE_Npo);
	ppst.setString(1, npo_id);
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
	public Npo_infoVO findByPrimaryKey(String npo_id) {
		
		Npo_infoVO npo_infoVO = null;
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_Npo);
			
			ppst.setString(1, npo_id);
			
			rs = ppst.executeQuery();
			
			while (rs.next()) {
				npo_infoVO = new Npo_infoVO();
				npo_infoVO.setNpo_id(rs.getString("npo_id")); //?
				npo_infoVO.setNpo_name(rs.getString("npo_name"));
				npo_infoVO.setNpo_image(rs.getBytes("npo_image"));
				npo_infoVO.setNpo_description(rs.getString("npo_description"));
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
		return npo_infoVO;
	}

	@Override
	public List<Npo_infoVO> getAll() {
		List<Npo_infoVO> list = new ArrayList<Npo_infoVO>();
		Npo_infoVO npo_infoVO = null;
		
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		
		try {
		
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareCall(GET_ALL_Npo);
			rs = ppst.executeQuery();
			
			while(rs.next()) {
				npo_infoVO = new Npo_infoVO();
				npo_infoVO.setNpo_id(rs.getString("npo_id"));
				npo_infoVO.setNpo_name(rs.getString("npo_name"));
				npo_infoVO.setNpo_image(rs.getBytes("npo_image"));
				npo_infoVO.setNpo_description(rs.getString("npo_description"));
				npo_infoVO.setCreate_time(rs.getTimestamp("create_time"));

				list.add(npo_infoVO);
			}
//		}catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
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
		Npo_infoJDBCDAO dao = new Npo_infoJDBCDAO();
		
		//新增 少image
//		Npo_infoVO npo_infoVO1 = new Npo_infoVO();
//		npo_infoVO1.setNpo_name("陳11");
//		npo_infoVO1.setNpo_description("他人很幽默");
//		dao.insert(npo_infoVO1);
		
		//修改 
//		Npo_infoVO npo_infoVO2 = new Npo_infoVO();
//		npo_infoVO2.setNpo_name("吳1");
//		npo_infoVO2.setNpo_description("安安您好");
//		npo_infoVO2.setNpo_id("1000000001");
//		dao.update(npo_infoVO2);
//		
		//刪除
//		dao.delete("1000000011");
		
//		Npo_infoVO npo_infoVO3 = dao.findByPrimaryKey("NID00001");
//		System.out.print(npo_infoVO3.getNpo_id() + ",");
//		
		//查全部 
//		List<Npo_infoVO> list = dao.getAll();
//		for (Npo_infoVO aNpo : list) {
//			System.out.print(aNpo.getNpo_id() + ",");
//			System.out.print(aNpo.getNpo_name() + ",");
//			System.out.print(aNpo.getNpo_description());
//			System.out.println();
//		}
		

		Integer total; 
		total= dao.findByDonationMoney("NID00001");
		System.out.print("HI:"+total);
		
	}

//	@SuppressWarnings("finally")
	@Override
	public Integer findByDonationMoney(String npo_id) {

		Npo_infoVO npo_infoVO = null;
		Connection con = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		
		try {
//			try {
//				Class.forName(driver);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ppst = con.prepareStatement(GET_ONE_Money);
			
			ppst.setString(1, npo_id);
			rs = ppst.executeQuery();
			
			System.out.println("RS: " +rs);
			Integer  total = null;
			while(rs.next()) {
				total = rs.getInt("total");
			System.out.println("total: "+total);
			}
			return total;
		}catch (SQLException se) {
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
	}
}
