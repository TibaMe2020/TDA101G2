package com.product_order_master.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order_detail.model.Order_detail_DAO;
import com.product_order_detail.model.Order_detail_VO;

public class Order_master_DAO implements Order_master_DAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO product_order_master (member_id, product_order_state,payment, develivery_location) VALUES(?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT product_order_id, member_id, product_order_state, payment, develivery_location, create_time FROM product_order_master WHERE product_order_id=?";
	private static final String GET_ALL_STMT = "SELECT product_order_id, member_id, product_order_state, payment, develivery_location, create_time FROM product_order_master ORDER BY product_order_id";
	private static final String GET_ONE_STMT_MEMBER_ID = "SELECT * FROM product_order_master WHERE member_id=?";
	private static final String GET_Detail_ByMasterno_STMT ="SELECT product_order_detail_id, product_order_id, product_version_id, quantity FROM product_order_detail WHERE product_order_id=? ORDER BY product_order_detail_id";
	private static final String UPDATE_STATE = "update product_order_master set product_order_state = ? where product_order_id = ?";
	
	
	
	@Override 
	public void updateState(String pid, String order_state) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATE);
			pstmt.setString(1, order_state);
			pstmt.setString(2, pid);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(Order_master_VO order_master_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_master_VO.getMember_id());
			pstmt.setString(2, order_master_VO.getProduct_order_state());
			pstmt.setString(3, order_master_VO.getPayment());
			pstmt.setString(4, order_master_VO.getLocation());
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
	public Order_master_VO findByPrimaryKey(String Order_master_id) {

		Order_master_VO order_master_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Order_master_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_master_VO = new Order_master_VO();
				order_master_VO.setOrder_master_id(rs.getString("product_order_id"));
				order_master_VO.setMember_id(rs.getString("member_id"));
				order_master_VO.setProduct_order_state(rs.getString("product_order_state"));
				order_master_VO.setPayment(rs.getString("payment"));
				order_master_VO.setLocation(rs.getString("develivery_location"));
				order_master_VO.setCreate_time(rs.getDate("create_time"));
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
		return order_master_VO;
	}

	@Override
	public List<Order_master_VO> getAll() {
		List<Order_master_VO> list = new ArrayList<Order_master_VO>();
		Order_master_VO order_master_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_master_VO = new Order_master_VO();
				order_master_VO.setOrder_master_id(rs.getString("product_order_id"));
				order_master_VO.setMember_id(rs.getString("member_id"));
				order_master_VO.setProduct_order_state(rs.getString("product_order_state"));
				order_master_VO.setPayment(rs.getString("payment"));
				order_master_VO.setLocation(rs.getString("develivery_location"));
				order_master_VO.setCreate_time(rs.getDate("create_time"));
				list.add(order_master_VO);
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
		return list;
	}
	
	@Override
	public List<Order_master_VO> getAllmember(String member_id) {
		List<Order_master_VO> list = new ArrayList<Order_master_VO>();
		Order_master_VO order_master_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMBER_ID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();


			

			while (rs.next()) {

				order_master_VO = new Order_master_VO();
				order_master_VO.setOrder_master_id(rs.getString("product_order_id"));
				order_master_VO.setMember_id(rs.getString("member_id"));
				order_master_VO.setProduct_order_state(rs.getString("product_order_state"));
				order_master_VO.setPayment(rs.getString("payment"));
				order_master_VO.setLocation(rs.getString("develivery_location"));
				order_master_VO.setCreate_time(rs.getDate("create_time"));
				list.add(order_master_VO);
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
		return list;
	}
	
	@Override
	public Set<Order_detail_VO> getDetailByMaster(String product_order_id) {
		Set<Order_detail_VO> set = new HashSet<Order_detail_VO>();
		Order_detail_VO order_detail_VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Detail_ByMasterno_STMT);
			pstmt.setString(1, product_order_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				order_detail_VO = new Order_detail_VO();
				order_detail_VO.setProduct_order_detail_id(rs.getString("product_order_detail_id"));
				order_detail_VO.setProduct_order_id(rs.getString("product_order_id"));
				order_detail_VO.setProduct_version_id(rs.getString("product_version_id"));
				order_detail_VO.setQuantity(rs.getInt("quantity"));
				set.add(order_detail_VO); // Store the row in the vector
			}
	
			// Handle any driver errors
		
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
		return set;
	}
	
	
	@Override
	public void insertWithDetail(Order_master_VO order_master_VO, List<Order_detail_VO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單主檔編號
			String cols[] = {"PRODUCT_ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, order_master_VO.getMember_id());
			pstmt.setString(2, order_master_VO.getProduct_order_state());
			pstmt.setString(3, order_master_VO.getPayment());
			pstmt.setString(4, order_master_VO.getLocation());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_product_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_product_order_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_product_order_id +"(剛新增成功的訂單主檔編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增明細
			Order_detail_DAO dao = new Order_detail_DAO();
			System.out.println("list.size()-A="+list.size());
			for (Order_detail_VO aDetail : list) {
				aDetail.setProduct_order_id(next_product_order_id) ;
				dao.insert2(aDetail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單主檔編號" + next_product_order_id + "時,共有訂單明細" + list.size()
					+ "人同時被新增");
			
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		
		Order_master_DAO dao = new Order_master_DAO();
		
		Order_master_VO order_master_VO = new Order_master_VO();
		order_master_VO.setMember_id("MB00004");
		order_master_VO.setProduct_order_state("2");
		order_master_VO.setPayment("1");
		order_master_VO.setLocation("新北市中和區永和路");
	
		
		List<Order_detail_VO> testList = new ArrayList<Order_detail_VO>(); // 準備置入訂單明細
		Order_detail_VO order_detail_XX = new Order_detail_VO();   // 訂單明細
		order_detail_XX.setProduct_order_id("POI00010");
		order_detail_XX.setProduct_version_id("PVI00008");
		order_detail_XX.setQuantity(2);

//		EmpVO empYY = new EmpVO();   // 員工POJO2
//		empYY.setEname("吳y");
//		empYY.setJob("MANAGER");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16000));
//		empYY.setComm(new Double(160));

		testList.add(order_detail_XX);
//		testList.add(empYY);
		
		dao.insertWithDetail(order_master_VO , testList);
		// INSERT
//		Order_master_VO order_master_insert = new Order_master_VO();
//		order_master_insert.setMember_id("");
//		order_master_insert.setProduct_order_state("2");
//		order_master_insert.setPayment("2");
//		order_master_insert.setLocation("");
//		dao.insert(order_master_insert);
//		System.out.println(order_master_insert);	

		// SELECT(ONE FOR PK)

//		Order_master_VO PomVOSelect = dao.findByPrimaryKey("POI00007");
//		System.out.println(PomVOSelect.getOrder_master_id() + "," + PomVOSelect.getMember_id() + "," + PomVOSelect.getProduct_order_state() + ","
//				+ PomVOSelect.getPayment() + "," + PomVOSelect.getLocation() + ","+PomVOSelect.getCreat_time()+",");
//		System.out.println("===============================");

		// SELECT(MORE)
//		List<Order_master_VO> list = dao.getAll();
//		for (Order_master_VO all : list) {
//			System.out.println(all.getOrder_master_id() + "," + all.getMember_id() + "," + all.getProduct_order_state() + ","
//					+ all.getPayment() + "," + all.getLocation() + ","+all.getCreat_time()+",");
//
//		}

		// SELECT(ONE FOR MEMBER_ID)
		String member_id = "MB00006";
		List<Order_master_VO> list = dao.getAllmember(member_id);
		for (Order_master_VO all : list) {
			System.out.println(all.getOrder_master_id() + "," + all.getMember_id() + "," + all.getProduct_order_state() + ","
					+ all.getPayment() + "," + all.getLocation() + ","+all.getCreate_time()+",");

		}

	}

	

	

	

	
}
