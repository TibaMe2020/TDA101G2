package com.store_order.model;

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

import com.store_order_detail.model.Store_order_detailDAO;
import com.store_order_detail.model.Store_order_detailVO;

import static com.common.Common.*;

public class Store_orderDAO implements Store_orderDAO_interface {
	
	private static DataSource datasource = null;
	static {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/PetBoxDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public final String INSERT=
			"insert into store_order(store_order_id, store_id, member_id,  store_order_name,"
			+ " store_order_email, store_order_phone_num, store_order_date_time, store_order_end_date,"
			+ " store_order_persons, store_order_payment, store_order_note, store_order_state)"
			+ " values('SO'||LPAD(to_char(seq_store_order_id.nextval),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public final String UPDATE=
			"update store_order set store_id=?, member_id=?,  store_order_name=?,"
			+ " store_order_email=?, store_order_phone_num=?, store_order_date_time=?, store_order_end_date=?,"
			+ " store_order_persons=?, store_order_payment=?, store_order_note=?, store_order_state=?"
			+ " where store_order_id=?";
	public final String DELETE=
			"delete from store_order where store_order_id=?";
	public final String selectByStore=
			"select * from store_order where store_id=? order by store_order_date_time desc";
	public final String selectByMember=
			"select * from store_order where member_id=? order by store_order_date_time desc";
	public final String  selectAll=
			"select * from store_order";
	
	@Override
	public void insert(Store_orderVO store_orderVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(INSERT);
			
			ps.setString(1, store_orderVO.getStore_id());
			ps.setString(2, store_orderVO.getMember_id());
			ps.setString(3, store_orderVO.getStore_order_name());
			ps.setString(4, store_orderVO.getStore_order_email());
			ps.setString(5, store_orderVO.getStore_order_phone_num());
			ps.setTimestamp(6, store_orderVO.getStore_order_date_time());
			ps.setDate(7, store_orderVO.getStore_order_end_date());
			ps.setObject(8, store_orderVO.getStore_order_persons(),java.sql.Types.INTEGER);
			ps.setString(9, store_orderVO.getStore_order_payment());
			ps.setString(10, store_orderVO.getStore_order_note());
			ps.setObject(11, store_orderVO.getStore_order_state(),java.sql.Types.INTEGER);
			ps.executeUpdate();
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver："+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured： " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Store_orderVO store_orderVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(UPDATE);
			
			ps.setString(1, store_orderVO.getStore_id());
			ps.setString(2, store_orderVO.getMember_id());
			ps.setString(3, store_orderVO.getStore_order_name());
			ps.setString(4, store_orderVO.getStore_order_email());
			ps.setString(5, store_orderVO.getStore_order_phone_num());
			ps.setTimestamp(6, store_orderVO.getStore_order_date_time());
			ps.setDate(7, store_orderVO.getStore_order_end_date());
			ps.setObject(8, store_orderVO.getStore_order_persons(), java.sql.Types.INTEGER);
			ps.setString(9, store_orderVO.getStore_order_payment());
			ps.setString(10, store_orderVO.getStore_order_note());
			ps.setObject(11, store_orderVO.getStore_order_state(), java.sql.Types.INTEGER);
			ps.setString(12, store_orderVO.getStore_order_id());
			ps.executeUpdate();
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver："+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured： " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String store_order_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(DELETE);
			
			ps.setString(1, store_order_id);
			ps.executeUpdate();
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver：" + e.getMessage()); 
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured： " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<Store_orderVO> selectByStore(String store_id) {
		List<Store_orderVO> list = new ArrayList<Store_orderVO>();
		Store_orderVO store_orderVO = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(selectByStore);
			ps.setString(1, store_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				store_orderVO = new Store_orderVO();
				store_orderVO.setStore_order_id(rs.getString("store_order_id"));
				store_orderVO.setMember_id(rs.getString("member_id"));
				store_orderVO.setStore_order_name(rs.getString("store_order_name"));
				store_orderVO.setStore_order_email(rs.getString("store_order_email"));
				store_orderVO.setStore_order_phone_num(rs.getString("store_order_phone_num"));
				store_orderVO.setStore_order_date_time(rs.getTimestamp("store_order_date_time"));
				store_orderVO.setStore_order_end_date(rs.getDate("store_order_end_date"));
				store_orderVO.setStore_order_persons(rs.getInt("store_order_persons"));
				store_orderVO.setStore_order_payment(rs.getString("store_order_payment"));
				store_orderVO.setStore_order_note(rs.getString("store_order_note"));
				store_orderVO.setStore_order_state(rs.getInt("store_order_state"));
				store_orderVO.setCreate_time(rs.getTimestamp("create_time"));
				
				list.add(store_orderVO);
			}

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver：" + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured： " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<Store_orderVO> selectByMember(String member_id) {
		List<Store_orderVO> list = new ArrayList<Store_orderVO>();
		Store_orderVO store_orderVO = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(selectByMember);
			ps.setString(1, member_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				store_orderVO = new Store_orderVO();
				store_orderVO.setStore_order_id(rs.getString("store_order_id"));
				store_orderVO.setStore_id(rs.getString("store_id"));
				store_orderVO.setStore_order_name(rs.getString("store_order_name"));
				store_orderVO.setStore_order_email(rs.getString("store_order_email"));
				store_orderVO.setStore_order_phone_num(rs.getString("store_order_phone_num"));
				store_orderVO.setStore_order_date_time(rs.getTimestamp("store_order_date_time"));
				store_orderVO.setStore_order_end_date(rs.getDate("store_order_end_date"));
				store_orderVO.setStore_order_persons(rs.getInt("store_order_persons"));
				store_orderVO.setStore_order_payment(rs.getString("store_order_payment"));
				store_orderVO.setStore_order_note(rs.getString("store_order_note"));
				store_orderVO.setStore_order_state(rs.getInt("store_order_state"));
				store_orderVO.setCreate_time(rs.getTimestamp("create_time"));
				
				list.add(store_orderVO);
			}

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver：" + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured： " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Store_orderVO> getAll(){
		List<Store_orderVO> list = new ArrayList<Store_orderVO>();
		Store_orderVO store_orderVO = null;
		
		try(Connection conn = datasource.getConnection(); PreparedStatement ps = conn.prepareStatement(selectAll);ResultSet rs = ps.executeQuery();){
		//JDBC
//		try(Connection conn = DriverManager.getConnection(url, userId, passWord); PreparedStatement ps = conn.prepareStatement(selectAll);ResultSet rs = ps.executeQuery();){
//			Class.forName(driver);
			
			while (rs.next()) {
				store_orderVO = new Store_orderVO();
				store_orderVO.setStore_order_id(rs.getString("store_order_id"));
				store_orderVO.setStore_id(rs.getString("store_id"));
				store_orderVO.setMember_id(rs.getString("member_id"));
				store_orderVO.setStore_order_name(rs.getString("store_order_name"));
				store_orderVO.setStore_order_email(rs.getString("store_order_email"));
				store_orderVO.setStore_order_phone_num(rs.getString("store_order_phone_num"));
				store_orderVO.setStore_order_date_time(rs.getTimestamp("store_order_date_time"));
				store_orderVO.setStore_order_end_date(rs.getDate("store_order_end_date"));
				store_orderVO.setStore_order_persons(rs.getInt("store_order_persons"));
				store_orderVO.setStore_order_payment(rs.getString("store_order_payment"));
				store_orderVO.setStore_order_note(rs.getString("store_order_note"));
				store_orderVO.setStore_order_state(rs.getInt("store_order_state"));
				store_orderVO.setCreate_time(rs.getTimestamp("create_time"));
				
				list.add(store_orderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//		throw new RuntimeException("Couldn't load database driver：" + e.getMessage());
		}
		return list;
	}
	@Override
	public void insertWithDetail(Store_orderVO store_orderVO, List<Store_order_detailVO> list) {
		PreparedStatement ps = null;
		try(Connection conn = datasource.getConnection();){
			// 1●設定於 pstm.executeUpdate()之前
    		conn.setAutoCommit(false);
    		// 先新增訂單
    		String cols[] = {"STORE_ORDER_ID"};
			ps = conn.prepareStatement(INSERT , cols);
			ps.setString(1, store_orderVO.getStore_id());
			ps.setString(2, store_orderVO.getMember_id());
			ps.setString(3, store_orderVO.getStore_order_name());
			ps.setString(4, store_orderVO.getStore_order_email());
			ps.setString(5, store_orderVO.getStore_order_phone_num());
			ps.setTimestamp(6, store_orderVO.getStore_order_date_time());
			ps.setDate(7, store_orderVO.getStore_order_end_date());
			ps.setObject(8, store_orderVO.getStore_order_persons(),java.sql.Types.INTEGER);
			ps.setString(9, store_orderVO.getStore_order_payment());
			ps.setString(10, store_orderVO.getStore_order_note());
			ps.setObject(11, store_orderVO.getStore_order_state(),java.sql.Types.INTEGER);
			ps.executeUpdate();
			//掘取對應的自增主鍵值
			String next_orderId = null;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				next_orderId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_orderId +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增明細
			Store_order_detailDAO dao = new Store_order_detailDAO();
			System.out.println("list.size()-A="+list.size());
			for (Store_order_detailVO detailVO : list) {
				detailVO.setStore_order_id(next_orderId);
				dao.insert2(detailVO,conn);
			}
			// 2●設定於 pstm.executeUpdate()之後
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_orderId + "時,共有明細" + list.size()
					+ "筆同時被新增");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
