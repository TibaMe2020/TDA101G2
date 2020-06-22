package com.notification.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotiJDBCDAO implements NotiDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PETBOX";
	String passwd = "123456";

//	private static final String UPDATE = "";
//	private static final String GET_ALL = "";
	private static final String INSERT = "INSERT INTO notification (notification_id, "
			+ " member_id, notification_class) VALUES (noti_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL = "SELECT notification_class, notification_id, create_time FROM notification where member_id = ?";
//	private static final String GET_ONE = "SELECT notification_class FROM notification where notification_id = ?";
	private static final String DELETE = "DELETE FROM notification where notification_id = ?";

	public static void main(String[] args) {
		NotiJDBCDAO dao = new NotiJDBCDAO();
		NotiVO notiVO = new NotiVO();
		for (NotiVO n : dao.findByMembId("MB00001")) {
			System.out.println(n.getNotification_class());
		}
//		notiVO.setMember_id("MB00001");
//		notiVO.setNotification_class(5);
//		dao.insert(notiVO);
//		dao.delete("NO00021");

	}

	public void insert(NotiVO notiVO) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(INSERT);) {
				ps.setString(1, notiVO.getMember_id());
				ps.setInt(2, notiVO.getNotification_class());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

	};

	public void delete(String notification_id) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(DELETE);) {
				ps.setString(1, notification_id);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	};

	public List<NotiVO> findByMembId(String member_id) {
		List<NotiVO> notis = new ArrayList<>();
		NotiVO notiVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_ALL);) {
				ps.setString(1, member_id);
				rs = ps.executeQuery();

				while (rs.next()) {
					notiVO = new NotiVO();
					notiVO.setNotification_id(rs.getString("notification_id"));
					notiVO.setNotification_class(rs.getInt("notification_class"));
					notiVO.setCreate_time(rs.getTimestamp("create_time"));
					notis.add(notiVO);
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
		return notis;
	}

	@Override
	public NotiVO findByPK(String notification_id) {
		return null;
	}


	@Override
	public void update(NotiVO notiVO) {
		// TODO Auto-generated method stub
		
	}
}
