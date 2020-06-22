package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberJDBCDAO implements MemberDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PETBOX";
	String passwd = "123456";

	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		MemberVO memberVO = new MemberVO();
		// GET_ONE
		System.out.println(dao.findByPK("MB00001"));
		// GET_BY_EMAIL
//		System.out.println(dao.findByEmail("igwt97jc@gmail.com"));
		// GET_APPLICANTS
//		for (MemberVO m : dao.getApplicants()) {
//			System.out.println(m);
//		}
		// GET_ALL
//		for (MemberVO m : dao.getAll()) {
//			System.out.println(m);
//		}
		// INSERT
//		memberVO.setEmail("apple@gmail.com");
//		memberVO.setPassword("apple");
//		memberVO.setName("黃衣二");
//		memberVO.setSex("男");
//		memberVO.setAddress("花蓮路中央街一段");
//		memberVO.setBirthday(new Date(1000000));
//		memberVO.setPhone_num("0987654321");
//		memberVO.setProfile_image(null);
//		memberVO.setNickname("hellooo");
//		memberVO.setPet_class("狗");
//		memberVO.setBlog_cover_image(null);
//		memberVO.setBlog_name("my diary");
//		memberVO.setMember_state(0);
//		memberVO.setId_number("A123456789");
//		memberVO.setBank_account("1235465465413");
//		memberVO.setDocument_image(null);
//		dao.insert(memberVO);
		// UPDATE
//		memberVO.setEmail("abble@gmail.com");
//		memberVO.setPassword("abble");
//		memberVO.setName("黃一三");
//		memberVO.setSex("男");
//		memberVO.setAddress("花蓮路中央街二段");
//		memberVO.setBirthday(new Date(1000000));
//		memberVO.setPhone_num("0987654321");
//		memberVO.setProfile_image(null);
//		memberVO.setNickname("hellooo");
//		memberVO.setPet_class("貓");
//		memberVO.setBlog_cover_image(null);
//		memberVO.setBlog_name("my diary");
//		memberVO.setMember_state(3);
//		memberVO.setId_number("A123000089");
//		memberVO.setBank_account("12300000005");
//		memberVO.setDocument_image(null);
//		memberVO.setMember_id("MB00018");
//		dao.update(memberVO);
		// Delete
//		dao.delete("MB00017");

	}

	private static final String INSERT = "INSERT INTO member (email, password, "
			+ "name, sex, address, birthday, phone_num, profile_image, nickname, pet_class, blog_cover_image, "
			+ "blog_name, member_state, id_number, bank_account, document_image"
			+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE member set email=?, password=?, name=?, sex=?, address=?, "
			+ "birthday=?, phone_num=?, profile_image=?, nickname=?, pet_class=?, blog_cover_image=?, "
			+ "blog_name=?, member_state=?, id_number=?, bank_account=?, document_image=? where member_id = ?";

	private static final String GET_ALL = "SELECT member_id, email, member_state, create_time "
			+ "FROM member ORDER BY CREATE_TIME";

	private static final String GET_ONE = "SELECT member_id, email, password, name, sex, address, to_char(birthday,'yyyy-mm-dd') birthday, "
			+ "phone_num, profile_image, nickname, pet_class, blog_cover_image, blog_name, "
			+ "create_time, update_time, member_state, id_number, bank_account, "
			+ "document_image, application_time FROM member WHERE member_id = ?";
	
	private static final String GET_BY_EMAIL = "SELECT member_id, email, password, name, sex, address, to_char(birthday,'yyyy-mm-dd') birthday, "
			+ "phone_num, profile_image, nickname, pet_class, blog_cover_image, blog_name, "
			+ "create_time, update_time, member_state, id_number, bank_account, "
			+ "document_image, application_time FROM member WHERE email = ?";

	private static final String GET_APPLICANTS = "SELECT member_id, email, name, phone_num, id_number, "
			+ "bank_account, document_image, application_time FROM member WHERE member_state = 2 ORDER BY application_time";

	private static final String DELETE = "DELETE FROM member where member_id = ?";

	public void insert(MemberVO memberVO) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(INSERT);) {

				ps.setString(1, memberVO.getEmail());
				ps.setString(2, memberVO.getPassword());
				ps.setString(3, memberVO.getName());
				ps.setString(4, memberVO.getSex());
				ps.setString(5, memberVO.getAddress());
				ps.setDate(6, memberVO.getBirthday());
				ps.setString(7, memberVO.getPhone_num());
				ps.setBytes(8, memberVO.getProfile_image());
				ps.setString(9, memberVO.getNickname());
				ps.setString(10, memberVO.getPet_class());
				ps.setBytes(11, memberVO.getBlog_cover_image());
				ps.setString(12, memberVO.getBlog_name());
				ps.setInt(13, memberVO.getMember_state());
				ps.setString(14, memberVO.getId_number());
				ps.setString(15, memberVO.getBank_account());
				ps.setBytes(16, memberVO.getDocument_image());

				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

	}

	public void update(MemberVO memberVO) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(UPDATE);) {
				ps.setString(1, memberVO.getEmail());
				ps.setString(2, memberVO.getPassword());
				ps.setString(3, memberVO.getName());
				ps.setString(4, memberVO.getSex());
				ps.setString(5, memberVO.getAddress());
				ps.setDate(6, memberVO.getBirthday());
				ps.setString(7, memberVO.getPhone_num());
				ps.setBytes(8, memberVO.getProfile_image());
				ps.setString(9, memberVO.getNickname());
				ps.setString(10, memberVO.getPet_class());
				ps.setBytes(11, memberVO.getBlog_cover_image());
				ps.setString(12, memberVO.getBlog_name());
				ps.setInt(13, memberVO.getMember_state());
				ps.setString(14, memberVO.getId_number());
				ps.setString(15, memberVO.getBank_account());
				ps.setBytes(16, memberVO.getDocument_image());
				ps.setString(17, memberVO.getMember_id());

				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	}

	public void delete(String member_id) {
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(DELETE);) {

				ps.setString(1, member_id);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

	}

	public MemberVO findByPK(String member_id) {
		MemberVO memberVO = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_ONE);) {

				ps.setString(1, member_id);
				rs = ps.executeQuery();

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setEmail(rs.getString("email"));
					memberVO.setPassword(rs.getString("password"));
					memberVO.setName(rs.getString("name"));
					memberVO.setSex(rs.getString("sex"));
					memberVO.setAddress(rs.getString("address"));
					memberVO.setBirthday(rs.getDate("birthday"));
					memberVO.setPhone_num(rs.getString("phone_num"));
					memberVO.setProfile_image(rs.getBytes("profile_image"));
					memberVO.setNickname(rs.getString("nickname"));
					memberVO.setPet_class(rs.getString("pet_class"));
					memberVO.setBlog_cover_image(rs.getBytes("blog_cover_image"));
					memberVO.setBlog_name(rs.getString("blog_name"));
					memberVO.setCreate_time(rs.getTimestamp("create_time"));
					memberVO.setUpdate_time(rs.getTimestamp("update_time"));
					memberVO.setMember_state(rs.getInt("member_state"));
					memberVO.setId_number(rs.getString("id_number"));
					memberVO.setBank_account(rs.getString("bank_account"));
					memberVO.setDocument_image(rs.getBytes("document_image"));
					memberVO.setApplication_time(rs.getTimestamp("application_time"));

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
		return memberVO;
	}

	public MemberVO findByEmail(String email) {
		MemberVO memberVO = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_BY_EMAIL);) {

				ps.setString(1, email);
				rs = ps.executeQuery();

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setEmail(rs.getString("email"));
					// 有安全性的問題
					memberVO.setPassword(rs.getString("password"));
					memberVO.setName(rs.getString("name"));
					memberVO.setSex(rs.getString("sex"));
					memberVO.setAddress(rs.getString("address"));
					memberVO.setBirthday(rs.getDate("birthday"));
					memberVO.setPhone_num(rs.getString("phone_num"));
					memberVO.setProfile_image(rs.getBytes("profile_image"));
					memberVO.setNickname(rs.getString("nickname"));
					memberVO.setPet_class(rs.getString("pet_class"));
					memberVO.setBlog_cover_image(rs.getBytes("blog_cover_image"));
					memberVO.setBlog_name(rs.getString("blog_name"));
					memberVO.setCreate_time(rs.getTimestamp("create_time"));
					memberVO.setUpdate_time(rs.getTimestamp("update_time"));
					memberVO.setMember_state(rs.getInt("member_state"));
					memberVO.setId_number(rs.getString("id_number"));
					memberVO.setBank_account(rs.getString("bank_account"));
					memberVO.setDocument_image(rs.getBytes("document_image"));
					memberVO.setApplication_time(rs.getTimestamp("application_time"));

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
		return memberVO;
	}

	public List<MemberVO> getAll() {

		List<MemberVO> members = new ArrayList<>();
		MemberVO memberVO = null;
		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_ALL);
					ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setEmail(rs.getString("email"));
					memberVO.setMember_state(rs.getInt("member_state"));
					memberVO.setCreate_time(rs.getTimestamp("create_time"));
					members.add(memberVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
		return members;

	}

	public List<MemberVO> getApplicants() {

		List<MemberVO> sellers = new ArrayList<>();
		MemberVO memberVO;

		try {
			Class.forName(driver);
			try (Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement(GET_APPLICANTS);
					ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setEmail(rs.getString("email"));
					memberVO.setName(rs.getString("name"));
					memberVO.setPhone_num(rs.getString("phone_num"));
					memberVO.setId_number(rs.getString("id_number"));
					memberVO.setBank_account(rs.getString("bank_account"));
					memberVO.setDocument_image(rs.getBytes("document_image"));
					memberVO.setApplication_time(rs.getTimestamp("application_time"));
					sellers.add(memberVO);
				}
				return sellers;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	}
}
