package com.store_closed.model;

import static com.store.common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Store_closedDAO implements Store_closedDAO_interface {
	
	private static DataSource datasource = null;
	static {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static final String INSERT =
			"insert into store_closed(store_closed_id, store_id, store_closed_day) values('SC'||LPAD(seq_store_closed_id.nextval,5,'0'), ?, ?)";
	public static final String DELETE =
			"delete from store_closed where store_closed_id=? ";
	public static final String SELECT =
			"select * from store_closed where store_id=? order by store_closed_day desc";
	
	@Override
	public void insert(Store_closedVO store_closedVO) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(INSERT);
			
			ps.setString(1, store_closedVO.getStore_id());
			ps.setDate(2, store_closedVO.getStore_closed_day());
			
			ps.executeUpdate();
		
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver： " + e.getMessage()); 
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
	public void delete(String store_closed_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(DELETE);
			
			ps.setString(1, store_closed_id);
			ps.executeUpdate();
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver： " + e.getMessage()); 
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
	public Set<Store_closedVO> selectByStore(String store_id) {
		Set<Store_closedVO> set = new LinkedHashSet<Store_closedVO>();
		Store_closedVO stcl = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			
			ps = conn.prepareStatement(SELECT);
			ps.setString(1, store_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				stcl = new Store_closedVO();
				stcl.setStore_closed_id(rs.getString("store_closed_id"));
				stcl.setStore_closed_day(rs.getDate("store_closed_day"));
				set.add(stcl); 
			}
		
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver： " + e.getMessage()); 
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
		return set;
	}
	
//	@Override
	public void insert2(Store_closedVO store_closedVO,Connection conn) {
		
		PreparedStatement ps = null;
		
		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userId, passWord);
			conn = datasource.getConnection();
			ps = conn.prepareStatement(INSERT);
			
			ps.setString(1, store_closedVO.getStore_id());
			ps.setDate(2, store_closedVO.getStore_closed_day());
			
			ps.executeUpdate();
		
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver： " + e.getMessage()); 
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("rollback error occured. "
						+ e1.getMessage());
			}
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

}
