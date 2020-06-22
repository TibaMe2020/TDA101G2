package com.admin.model;

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

public class AdminDAO implements AdminDAO_interface {

	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO admin (admin_account, "
			+ "admin_password, suspension) VALUES (?, ?, 0)";
	private static final String UPDATE = "UPDATE admin set admin_password = ?, suspension = ? where admin_id = ?";
	private static final String GET_ONE = "SELECT admin_id, admin_account, admin_password, suspension, create_time, update_time "
			+ "from admin where admin_id = ?";
	private static final String GET_BY_ADMIN_ACCOUNT = "SELECT admin_id, admin_account, admin_password, suspension, create_time, update_time "
			+ "from admin where admin_account = ?";
	private static final String GET_ALL = "SELECT admin_id, admin_account, suspension, create_time, update_time "
			+ "from admin";
	private static final String DELETE = "DELETE FROM admin where admin_id = ?";

	public void insert(AdminVO adminVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT);) {
			ps.setString(1, adminVO.getAdmin_account());
			ps.setString(2, adminVO.getAdmin_password());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

	public void update(AdminVO adminVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE);) {
			ps.setString(1, adminVO.getAdmin_password());
			ps.setInt(2, adminVO.getSuspension());
			ps.setString(3, adminVO.getAdmin_id());
			System.out.println("DAO id : " + adminVO.getAdmin_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

	public void delete(String admin_id) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE);) {
			ps.setString(1, admin_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

//	SELECT admin_account, suspension, create-time, update_time

	public AdminVO findByPK(String admin_id) {
		AdminVO adminVO = null;
		ResultSet rs = null;

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ONE);) {

			ps.setString(1, admin_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdmin_id(rs.getString("admin_id"));
				adminVO.setAdmin_account(rs.getString("admin_account"));
				adminVO.setAdmin_password(rs.getString("admin_password"));
				adminVO.setSuspension(rs.getInt("suspension"));
				adminVO.setCreate_time(rs.getTimestamp("create_time"));
				adminVO.setUpdate_time(rs.getTimestamp("update_time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return adminVO;

	};

	public AdminVO findByAccount(String admin_account) {
		AdminVO adminVO = null;
		ResultSet rs = null;

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_ADMIN_ACCOUNT);) {

			ps.setString(1, admin_account);
			rs = ps.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdmin_id(rs.getString("admin_id"));
				adminVO.setAdmin_account(rs.getString("admin_account"));
				adminVO.setAdmin_password(rs.getString("admin_password"));
				adminVO.setSuspension(rs.getInt("suspension"));
				adminVO.setCreate_time(rs.getTimestamp("create_time"));
				adminVO.setUpdate_time(rs.getTimestamp("update_time"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return adminVO;

	};

//	SELECT admin_id, admin_account, suspension, create_time, update_time
	public List<AdminVO> getAll() {
		List<AdminVO> admins = new ArrayList<>();
		AdminVO adminVO = null;

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdmin_id(rs.getString("admin_id"));
				adminVO.setAdmin_account(rs.getString("admin_account"));
				adminVO.setSuspension(rs.getInt("suspension"));
				adminVO.setCreate_time(rs.getTimestamp("create_time"));
				adminVO.setUpdate_time(rs.getTimestamp("update_time"));
				admins.add(adminVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

		return admins;
	};

}
