package com.member.model;

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

public class MemberDAO implements MemberDAO_interface {

	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO member (email, password, "
			+ "name, sex, address, birthday, phone_num, profile_image, nickname, pet_class, blog_cover_image, "
			+ "blog_name, member_state, id_number, bank_account, document_image"
			+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE member set email=?, password=?, name=?, sex=?, address=?, " + 
			"birthday=?, phone_num=?, profile_image=?, nickname=?, pet_class=?, blog_cover_image=?, " + 
			"blog_name=?, member_state=?, id_number=?, bank_account=?, document_image=? where email = ?";

	private static final String GET_ALL = "SELECT member_id, name,email, member_state, create_time "
			+ "FROM member ORDER BY CREATE_TIME";
	
	private static final String GET_BLOGER_INFO = "SELECT member_id, profile_image, nickname, "
			+ "pet_class, blog_cover_image, blog_name, create_time FROM member ORDER BY CREATE_TIME";

	private static final String GET_ONE = "SELECT member_id, email, password, name, sex, address, to_char(birthday,'yyyy-mm-dd') birthday, "
			+ "phone_num, profile_image, nickname, pet_class, blog_cover_image, blog_name, "
			+ "create_time, update_time, member_state, id_number, bank_account, "
			+ "document_image, application_time FROM member WHERE member_id = ?";

	private static final String GET_BY_EMAIL = "SELECT member_id, email, password, name, sex, address, to_char(birthday,'yyyy-mm-dd') birthday, "
			+ "phone_num, profile_image, nickname, pet_class, blog_cover_image, blog_name, "
			+ "create_time, update_time, member_state, id_number, bank_account, "
			+ "document_image, application_time FROM member WHERE email = ?";

	private static final String GET_APPLICANTS = "SELECT member_id, email, name, phone_num, id_number, address, "
			+ "bank_account, document_image, create_time, application_time FROM member WHERE member_state = 2 ORDER BY application_time DESC";

	private static final String DELETE = "DELETE FROM member where member_id = ?";

	public void insert(MemberVO memberVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT);) {
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

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	}

	public void update(MemberVO memberVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE);) {

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
			ps.setString(17, memberVO.getEmail());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	}

	public void delete(String member_id) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE);) {

			ps.setString(1, member_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

	}

	public MemberVO findByPK(String member_id) {
		MemberVO memberVO = null;
		ResultSet rs = null;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ONE);) {
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
		return memberVO;
	}

	public MemberVO findByEmail(String email) {
		MemberVO memberVO = null;
		ResultSet rs = null;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_EMAIL);) {

			ps.setString(1, email);
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
		return memberVO;
	}

	public List<MemberVO> getAll() {

		List<MemberVO> members = new ArrayList<>();
		MemberVO memberVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setName(rs.getString("name"));
				memberVO.setMember_state(rs.getInt("member_state"));
				memberVO.setCreate_time(rs.getTimestamp("create_time"));
				members.add(memberVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
		return members;
	}
	
	public List<MemberVO> getAllBlogerInfo() {

		List<MemberVO> members = new ArrayList<>();
		MemberVO memberVO = null;
		try {
			
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con.prepareStatement(GET_BLOGER_INFO);
					ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setProfile_image(rs.getBytes("profile_image"));
					memberVO.setNickname(rs.getString("nickname"));
					memberVO.setPet_class(rs.getString("pet_class"));
					memberVO.setBlog_cover_image(rs.getBytes("blog_cover_image"));
					memberVO.setBlog_name(rs.getString("blog_name"));
					memberVO.setCreate_time(rs.getTimestamp("create_time"));
					members.add(memberVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
		return members;

	}

	public List<MemberVO> getApplicants() {

		List<MemberVO> applicants = new ArrayList<>();
		MemberVO memberVO;

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_APPLICANTS);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhone_num(rs.getString("phone_num"));
				memberVO.setId_number(rs.getString("id_number"));
				memberVO.setBank_account(rs.getString("bank_account"));
				memberVO.setDocument_image(rs.getBytes("document_image"));
				memberVO.setApplication_time(rs.getTimestamp("application_time"));
				memberVO.setCreate_time(rs.getTimestamp("create_time"));
				applicants.add(memberVO);
			}

			return applicants;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

	}

}
