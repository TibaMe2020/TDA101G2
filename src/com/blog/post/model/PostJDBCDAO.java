package com.blog.post.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.blog.post.model.PostVO;
public class PostJDBCDAO implements PostDAOInterface{
	String driverClass = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "PETBOX";
	String password = "123456";
	
	private static final String insert = "insert into post(" + "post_id, member_id, post_class, post_image1, "
			+ "post_image2, post_image3, post_image4, post_image5, post_content) " 
			+ "values('PID' || LPAD(POST_ID_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "update post set post_class = ?, post_image1 = ?, post_image2 = ?, "
			+ "post_image3 = ?, post_image4 = ?, post_image5 = ?, post_content = ? where post_id = ?";
	private static final String delete = "delete from post where post_id = ?";
	private static final String get_one = "select * from post where post_id = ?";
	private static final String get_all = "select * from post order by post_id desc";
	private static final String get_by_member_id = "select * from post where member_id = ? order by create_time desc";
	private static final String get_by_post_class = "select * from post where post_class = ? order by create_time desc";
	private static final String get_by_create_time = "select * from (select * from post order by create_time desc) where rownum <= 5";
	private static final String get_by_post_like = "select * from (select * from post order by post_like desc nulls last) where rownum <= 5";
	private static final String get_by_create_time_member_id = "select * from (select * from post order by create_time desc) where rownum <= 5 and member_id = ?";
	private static final String get_by_post_like_member_id = "select * from (select * from post order by post_like desc nulls last) where rownum <= 5 and member_id = ?";
	private static final String get_by_create_time_post_class = "select * from (select * from post order by create_time desc) where rownum <= 5 and post_class = ?";
	private static final String get_by_post_like_post_class = "select * from (select * from post order by post_like desc nulls last) where rownum <= 5 and post_class = ?";
	private static final String get_by_create_time_post_class_member_id = "select * from (select * from post order by create_time desc) where rownum <= 5 and post_class = ? and member_id = ?";
	private static final String get_by_post_like_post_class_member_id = "select * from (select * from post order by post_like desc nulls last) where rownum <= 5 and post_class = ? and member_id = ?";
	private static final String get_by_member_id_post_class = "select * from post where member_id = ? and post_class = ? order by post_id desc";
	private static final String get_by_post_id = "update post set post_like = ? where post_id = ?";
	private static final String updateMessageCount = "update post set post_message_count = ? where post_id = ?";
	private static final String updateShareCount = "update post set post_share = ? where post_id = ?";
	private static final String getPostContent = "select substr(post_content, 1, 25) as postcontent20, post_id from post where post_id = ?";
	private static final String getPostCount = "select count(post_id) from post";
	
	@Override
	public void insert(PostVO postVO){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {			
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(insert);
									
			stmt.setString(1, postVO.getMember_id());
			stmt.setString(2, postVO.getPost_class());
			stmt.setBytes(3, postVO.getPost_image1());
			stmt.setBytes(4, postVO.getPost_image2());
			stmt.setBytes(5, postVO.getPost_image3());
			stmt.setBytes(6, postVO.getPost_image4());
			stmt.setBytes(7, postVO.getPost_image5());
			stmt.setString(8, postVO.getPost_content());

			stmt.executeUpdate();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	@Override
	public void update(PostVO postVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(update);
			
			stmt.setString(1, postVO.getPost_class());
			stmt.setBytes(2, postVO.getPost_image1());
			stmt.setBytes(3, postVO.getPost_image2());
			stmt.setBytes(4, postVO.getPost_image3());
			stmt.setBytes(5, postVO.getPost_image4());
			stmt.setBytes(6, postVO.getPost_image5());
			stmt.setString(7, postVO.getPost_content());
			stmt.setString(8, postVO.getPost_id());
			stmt.executeUpdate();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void delete(String post_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(delete);
			
			stmt.setString(1, post_id);
			stmt.executeUpdate();	
	
			conn.commit();
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}	
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public PostVO findByPrimaryKey(String post_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PostVO postVO = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_one);
						
			stmt.setString(1, post_id);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return postVO;
	}
	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_all);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PostVO> getByMemberId(String member_id) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_member_id);
			
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PostVO> getByPostClass(String post_class) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_post_class);
			
			stmt.setString(1, post_class);
			rs = stmt.executeQuery();
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PostVO> getFifthCreateTime() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_create_time);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PostVO> getFifthPostLike() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_post_like);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PostVO> getFifthCreateTime(String post_class) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_create_time_post_class);
			stmt.setString(1, post_class);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public List<PostVO> getFifthPostLike(String post_class) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_post_like_post_class);
			stmt.setString(1, post_class);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public List<PostVO> getFifthCreateTime(String post_class, String member_id) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_create_time_post_class_member_id);
			stmt.setString(1, post_class);
			stmt.setString(2, member_id);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;

	}
	@Override
	public List<PostVO> getFifthPostLike(String post_class, String member_id) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_post_like_post_class_member_id);
			stmt.setString(1, post_class);
			stmt.setString(2, member_id);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}	
	@Override
	public List<PostVO> getFifthCreateTimeMemberId(String member_id) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_create_time_member_id);
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public List<PostVO> getFifthPostLikeMemberId(String member_id) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_post_like_member_id);
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PostVO> getByMemberId(String member_id, String post_class) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_member_id_post_class);
			stmt.setString(1, member_id);
			stmt.setString(2, post_class);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_image1(rs.getBytes("post_image1"));
				postVO.setPost_image2(rs.getBytes("post_image2"));
				postVO.setPost_image3(rs.getBytes("post_image3"));
				postVO.setPost_image4(rs.getBytes("post_image4"));
				postVO.setPost_image5(rs.getBytes("post_image5"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(postVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}	
	@Override
	public void updateByPostId(String post_id, Integer post_like) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(get_by_post_id);
			
			stmt.setInt(1, post_like);
			stmt.setString(2, post_id);
			
			stmt.executeUpdate();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updateMessageCountByPostId(String post_id, Integer post_message_count) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(updateMessageCount);
			
			stmt.setInt(1, post_message_count);
			stmt.setString(2, post_id);
			
			stmt.executeUpdate();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	@Override
	public void updateShareCountByPostId(String post_id, Integer post_share) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(updateShareCount);
			
			stmt.setInt(1, post_share);
			stmt.setString(2, post_id);
			
			stmt.executeUpdate();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Integer getPostCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer postCount = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(getPostCount);
			rs = stmt.executeQuery();
			while(rs.next()) {
				postCount = rs.getInt(1);
//				postCount = rs.getInt("count(post_id)");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return postCount;
	}
		
	public static void main(String[] args) {
		PostJDBCDAO test = new PostJDBCDAO();
		//insert
//		byte[] image = null;
//		try {
//			File input = new File("C:/image/img05.jpg");
//			FileInputStream fis = new FileInputStream(input);
//			image = new byte[fis.available()];
//			fis.read(image);
//			
//			PostVO postVO = new PostVO();
//			postVO.setMember_id("MB00001");
//			postVO.setPost_class("哈哈");
//			postVO.setPost_image1(image);
//			postVO.setPost_content("呵呵呵呵111111111111111111");
//			postVO.setPost_like(0);
//			postVO.setPost_message_count(0);
//			postVO.setPost_share(0);
//			test.insert(postVO);
//			
//			fis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
				
		//update
//		PostVO postVO1 = new PostVO();
//		postVO1.setPost_class("哈哈");
//		postVO1.setPost_image1(null);
//		postVO1.setPost_content("啦啦啦啦啦");
//		postVO1.setPost_id("PID00011");
//		test.update(postVO1);
		
		//delete
//		test.delete("PID00011");
		
		//select
//		PostVO postVO2 = test.findByPrimaryKey("PID00010");
//		System.out.println(postVO2.toString());
		
		//getAll
//		List<PostVO> list1 = new ArrayList<PostVO>();
//		list1 = test.getAll();
//		for(PostVO postVO : list1) {
//			System.out.println(postVO.toString());	
//			System.out.println();
//		}
		
		//getByMemberId
//		List<PostVO> list2 = new ArrayList<PostVO>();
//		list2 = test.getByMemberId("MB00004");
//		for(PostVO postVO : list2) {
//			System.out.println(postVO.toString());	
//			System.out.println();
//		}
		
		//getByPostClass
//		List<PostVO> list3 = new ArrayList<PostVO>();
//		list3 = test.getByPostClass("生活");
//		for(PostVO postVO : list3) {
//			System.out.println(postVO.toString());	
//			System.out.println();
//		}
		
		//getFifthCreateTime
//		List<PostVO> list4 = new ArrayList<PostVO>();
//		list4 = test.getAllCreateTime();
//		for(PostVO postVO : list4) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthPostlike
//		List<PostVO> list5 = new ArrayList<PostVO>();
//		list5 = test.getAllPostLike();
//		for(PostVO postVO : list5) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthCreateTime(String post_class)
//		List<PostVO> list6 = new ArrayList<PostVO>();
//		list6 = test.getFifthCreateTime("生活");
//		for(PostVO postVO : list6) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthPostLike(String post_class)
//		List<PostVO> list7 = new ArrayList<PostVO>();
//		list7 = test.getFifthPostLike("生活");
//		for(PostVO postVO : list7){
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthCreateTime(String post_class, String member_id)
//		List<PostVO> list8 = new ArrayList<PostVO>();
//		list8 = test.getFifthCreateTime("生活", "MB00001");
//		for(PostVO postVO : list8) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthPostLike(String post_class, String member_id)
//		List<PostVO> list9 = new ArrayList<PostVO>();
//		list9 = test.getFifthPostLike("生活", "MB00001");
//		for(PostVO postVO : list9){
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthCreateTime(String member_id)
//		List<PostVO> list10 = new ArrayList<PostVO>();
//		list10 = test.getFifthCreateTimeMemberId("MB00001");
//		for(PostVO postVO : list10) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getFifthPostLike(String member_id)
//		List<PostVO> list11 = new ArrayList<PostVO>();
//		list11 = test.getFifthPostLikeMemberId("MB00001");
//		for(PostVO postVO : list11) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//getByMemberId(String member_id, String post_class)
//		List<PostVO> list12 = new ArrayList<PostVO>();
//		list12 = test.getByMemberId("MB00001", "生活");
//		for(PostVO postVO : list12) {
//			System.out.println(postVO.toString());
//			System.out.println();
//		}
		//updateByPostId(String post_id, Integer post_like)
//		test.updateByPostId("PID00012", 13);
		
		//updateMessageCountByPostId(String post_id, Integer post_message_count)
//		test.updateMessageCountByPostId("PID00010", 20);
		
		//updateShareCountByPostId(String posts_id)
//		test.updateShareCountByPostId("PID00010", 6);
		
		//getPostCount
		System.out.println(test.getPostCount());
		
		//getPostContent
		System.out.println(test.getPostContent("PID00001"));
	}
	@Override
	public PostVO getPostContent(String post_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PostVO postContent = new PostVO();
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(getPostContent);
			stmt.setString(1, post_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				postContent.setPost_id(rs.getString("post_id"));
				postContent.setPost_content(rs.getString("postcontent20"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return postContent;
	}
	
		
}
