package com.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminJDBCDAO implements AdminDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PETBOX";
	String passwd = "123456";

	public static void main(String[] args) {
		AdminJDBCDAO dao = new AdminJDBCDAO();
		AdminVO adminVO = new AdminVO();
		
		
//		String str = "apple";
//		String str1 = "";
//		String str2 = null;
//		Map<String, ArrayList<String>> notiMap = new HashMap<>();
//		notiMap.put("class1", new ArrayList<String>(Arrays.asList("您的包裹已送達XXX門市，請記得去領取", "AD1200")));
//		notiMap.put("class2", new ArrayList<String>(Arrays.asList("您的包裹已送達XXX門市，請記得去領取", "AD1200")));
//		notiMap.put("class3", new ArrayList<String>(Arrays.asList("您的包裹已送達XXX門市，請記得去領取", "AD1200")));
//		notiMap.put("class4", new ArrayList<String>(Arrays.asList("您的包裹已送達XXX門市，請記得去領取", "AD1200")));
//		notiMap.put("class5", new ArrayList<String>(Arrays.asList("您的包裹已送達XXX門市，請記得去領取", "AD1200")));
//		notiMap.put("class6", new ArrayList<String>(Arrays.asList("您的包裹已送達XXX門市，請記得去領取", "AD1200")));
//		for(ArrayList<String> noti : notiMap.values()) {
//			System.out.println(noti.get(0));
//		}
		// GET_ONE
//		System.out.println(dao.findByAccount("admin1").getAdmin_id());
//		if("".equals(str2)) {
//			System.out.println("It's empty string");			
//		} else {
//			System.out.println("not empty or null");
//		}
		// INSERT
//		adminVO.setAdmin_account("admin6");
//		adminVO.setAdmin_password("123");
//		dao.insert(adminVO);
		// UPDATE
//		adminVO.setSuspension(1);
//		adminVO.setAdmin_id("AD00001");
//		System.out.println(adminVO);
//		dao.update(adminVO);
		// Delete
//		dao.delete(8);
		// GET_ALL
//		for (AdminVO a : dao.getAll()) {
//			System.out.println(a);
//		}
		
		
		

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
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(INSERT);) {
				ps.setString(1, adminVO.getAdmin_account());
				ps.setString(2, adminVO.getAdmin_password());

				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

	public void update(AdminVO adminVO) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(UPDATE);) {
				ps.setString(1, adminVO.getAdmin_password());
				ps.setInt(2, adminVO.getSuspension());
				ps.setString(3, adminVO.getAdmin_id());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

	public void delete(String admin_id) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(DELETE);) {
				ps.setString(1, admin_id);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

//	SELECT admin_account, suspension, create-time, update_time

	public AdminVO findByPK(String admin_id) {
		AdminVO adminVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_ONE);) {

				ps.setString(1, admin_id);
				rs = ps.executeQuery();

				while (rs.next()) {
					rs.next();
					adminVO = new AdminVO();
					adminVO.setAdmin_account(rs.getString("admin_account"));
					adminVO.setAdmin_password(rs.getString("admin_password"));
					adminVO.setSuspension(rs.getInt("suspension"));
					adminVO.setCreate_time(rs.getTimestamp("create_time"));
					adminVO.setUpdate_time(rs.getTimestamp("update_time"));
				}
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

	public AdminVO findByAccount(String admin_account) {
		AdminVO adminVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_BY_ADMIN_ACCOUNT);) {

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
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

		return admins;
	};

}
