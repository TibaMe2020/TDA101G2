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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.blog.post.model.PostVO;
public class PostJNDIDAO implements PostDAOInterface{
	
	private static DataSource dataSource = null;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
	private static final String insert = "insert into post(" + "post_id, member_id, post_class, post_image1, "
			+ "post_image2, post_image3, post_image4, post_image5, post_content) " 
			+ "values('PID' || LPAD(POST_ID_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "update post set post_class = ?, post_image1 = ?, post_image2 = ?, "
			+ "post_image3 = ?, post_image4 = ?, post_image5 = ?, post_content = ? where post_id = ?";
	private static final String delete = "delete from post where post_id = ?";
	private static final String get_one = "select * from post where post_id = ?";
	private static final String get_all = "select post_id,member_id,post_class,post_content,post_like,post_message_count,post_share,create_time,update_time from post order by post_id desc";
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
	private static final String getPostLikeMost = "SELECT * FROM (SELECT MEMBER_ID, "
			+ "SUM(POST_LIKE)AS ALL_POST_LIKE FROM POST GROUP BY MEMBER_ID ORDER BY ALL_POST_LIKE DESC) WHERE ROWNUM <= 3";
	
	@Override
	public void insert(PostVO postVO){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {			
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(delete);
			
			stmt.setString(1, post_id);
			stmt.executeUpdate();	
	
			conn.commit();
			conn.setAutoCommit(true);
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
			conn = dataSource.getConnection();
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
				postVO.setUpdate_time(rs.getTimestamp("update_time"));;
			}
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
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_all);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getString("post_id"));
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_like(rs.getInt("post_like"));
				postVO.setPost_message_count(rs.getInt("post_message_count"));
				postVO.setPost_share(rs.getInt("post_share"));
				postVO.setCreate_time(rs.getTimestamp("create_time"));
				postVO.setUpdate_time(rs.getTimestamp("update_time"));
				
//				postVO.setPost_image1(rs.getBytes("post_image1"));
//				postVO.setPost_image2(rs.getBytes("post_image2"));
//				postVO.setPost_image3(rs.getBytes("post_image3"));
//				postVO.setPost_image4(rs.getBytes("post_image4"));
//				postVO.setPost_image5(rs.getBytes("post_image5"));
				list.add(postVO);
			}
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_by_post_id);
			
			stmt.setInt(1, post_like);
			stmt.setString(2, post_id);
			
			stmt.executeUpdate();	
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
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(updateMessageCount);
			
			stmt.setInt(1, post_message_count);
			stmt.setString(2, post_id);
			
			stmt.executeUpdate();	
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
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(updateShareCount);
			
			stmt.setInt(1, post_share);
			stmt.setString(2, post_id);
			
			stmt.executeUpdate();	
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
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(getPostCount);
			rs = stmt.executeQuery();
			while(rs.next()) {
				postCount = rs.getInt(1);
//				postCount = rs.getInt("count(post_id)");
			}
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
	@Override
	public PostVO getPostContent(String post_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PostVO postContent = new PostVO();
		ResultSet rs = null;
		
		try {
			conn= dataSource.getConnection();
			stmt = conn.prepareStatement(getPostContent);
			stmt.setString(1, post_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				postContent.setPost_id(rs.getString("post_id"));
				postContent.setPost_content(rs.getString("postcontent20"));
			}
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

	@Override
	public List<PostVO> getPostLikeMost() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(getPostLikeMost);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setMember_id(rs.getString("member_id"));
				postVO.setPost_like(rs.getInt("all_post_like"));
				list.add(postVO);
			}
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
	
}
