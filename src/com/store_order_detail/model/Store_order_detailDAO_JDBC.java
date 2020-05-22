package com.store_order_detail.model;

import static com.common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.store_order.model.Store_orderVO;

public class Store_order_detailDAO_JDBC implements Store_order_detail_interface {
	
	public final String INSERT=
			"insert into store_order_detail(store_order_detail_id, store_order_id, service_id, order_detail_pets)"
			+ " values('SD'||LPAD(seq_store_order_id.nextval,5,'0'), ?, ?, ?)";
	public final String UPDATE=
			"update store_order_detail set store_order_id=?, service_id=?, order_detail_pets=? where store_order_detail_id=?";
	public final String DELETE=
			"delete from store_order_detail where store_order_detail_id=?";
	public final String selectByOrderId=
			"select * from store_order_detail where store_order_id=?";

	@Override
	public void insert(Store_order_detailVO store_order_detailVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(INSERT);
			
//			ps.setString(1, store_order_detailVO.getStore_order_detail_id());
			ps.setString(1, store_order_detailVO.getStore_order_id());
			ps.setString(2, store_order_detailVO.getService_id());
			ps.setInt(3, store_order_detailVO.getOrder_detail_pets());

			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver： "+e.getMessage());
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
	public void update(Store_order_detailVO store_order_detailVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(UPDATE);
			
			ps.setString(1, store_order_detailVO.getStore_order_id());
			ps.setString(2, store_order_detailVO.getService_id());
			ps.setInt(3, store_order_detailVO.getOrder_detail_pets());
			ps.setString(4, store_order_detailVO.getStore_order_detail_id());
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver： "+e.getMessage());
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
	public void delete(String store_order_detail_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(DELETE);
			
			ps.setString(1, store_order_detail_id);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver： " + e.getMessage()); 
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
	public List<Store_order_detailVO> selectByOrderId(String store_order_id) {
		List<Store_order_detailVO> list = new ArrayList<Store_order_detailVO>();
		Store_order_detailVO store_order_detailVO = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(selectByOrderId);
			ps.setString(1, store_order_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				store_order_detailVO = new Store_order_detailVO();
				store_order_detailVO.setStore_order_detail_id(rs.getString("store_order_detail_id"));
				store_order_detailVO.setStore_order_id(rs.getString("store_order_id"));
				store_order_detailVO.setService_id(rs.getString("service_id"));
				store_order_detailVO.setOrder_detail_pets(rs.getInt("order_detail_pets"));
	
				
				list.add(store_order_detailVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver： " + e.getMessage());
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

}
