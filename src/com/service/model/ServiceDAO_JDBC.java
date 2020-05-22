package com.service.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.store.model.StoreVO;

import static com.common.Common.*;

public class ServiceDAO_JDBC implements ServiceDAO_interface {

	public static final String INSERT= 
			"insert into service(service_id, store_id, service_detail, service_price, service_limit, " + 
			"service_time, service_state) values('SE'||LPAD(seq_service_id.nextval,5,'0'), ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE= 
			"update service set store_id=?, service_detail=?, service_price=?, service_limit=?, " + 
			"service_time=?, service_state=? where service_id=?";
	public static final String DELETE=
			"delete from service where service_id=? ";
	public static final String SELECT=
			"select *  from service where store_id=? ";
	
	@Override
	public void insert(ServiceVO serviceVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(INSERT);
			
//			ps.setString(1, serviceVO.getService_id());
			ps.setString(1, serviceVO.getStore_id());
			ps.setString(2, serviceVO.getService_detail());
			ps.setInt(3, serviceVO.getService_price());
			ps.setObject(4, serviceVO.getService_limit(), java.sql.Types.INTEGER);
			ps.setObject(5, serviceVO.getService_time(), java.sql.Types.INTEGER);
			ps.setObject(6, serviceVO.getService_state(), java.sql.Types.INTEGER);
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
	public void update(ServiceVO serviceVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(UPDATE);
			
			ps.setString(1, serviceVO.getStore_id());
			ps.setString(2, serviceVO.getService_detail());
			ps.setInt(3, serviceVO.getService_price());
			ps.setObject(4, serviceVO.getService_limit(), java.sql.Types.INTEGER);
			ps.setObject(5, serviceVO.getService_time(), java.sql.Types.INTEGER);
			ps.setObject(6, serviceVO.getService_state(), java.sql.Types.INTEGER);
			ps.setString(7, serviceVO.getService_id());
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
	public void delete(String service_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(DELETE);
			
			ps.setString(1, service_id);
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
	public List<ServiceVO> selectByStore(String store_id) {
		List<ServiceVO> list = new ArrayList<ServiceVO>();
		ServiceVO serviceVO = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			ps = conn.prepareStatement(SELECT);
			ps.setString(1, store_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				serviceVO = new ServiceVO();
				serviceVO.setService_id(rs.getString("service_id"));
				serviceVO.setService_detail(rs.getString("service_detail"));
				serviceVO.setService_price(rs.getInt("service_price"));
				serviceVO.setService_limit(rs.getInt("service_limit"));
				serviceVO.setService_time(rs.getInt("service_time"));
				serviceVO.setService_state(rs.getInt("service_state"));
				
				list.add(serviceVO);
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
