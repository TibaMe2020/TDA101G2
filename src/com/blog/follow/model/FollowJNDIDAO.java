package com.blog.follow.model;

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

public class FollowJNDIDAO 	implements FollowDAOInterface{
	
	private static DataSource dataSource = null;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String insert = "insert into follow(follow_id, member_id, followed_member_id) values('FID' || LPAD(FOLLOW_ID_SEQ.NEXTVAL, 5, '0'), ?, ?)";
	private static final String delete = "delete from follow where follow_id = ?";
	private static final String get_one = "select * from follow where follow_id = ?";
	private static final String get_all = "select * from follow";
	private static final String get_by_member_id = "select * from follow where member_id = ?";
	private static final String get_by_followed_member_id = "select * from follow where followed_member_id = ?";
	
	@Override
	public void insert(FollowVO followVO) {
		Connection conn = null;
		PreparedStatement stmt = null; 
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(insert);
			
			stmt.setString(1, followVO.getMember_id());
			stmt.setString(2, followVO.getFollowed_member_id());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}		
	}

	@Override
	public void delete(String follow_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(delete);
			
			stmt.setString(1, follow_id);
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
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}		
		
	}

	@Override
	public FollowVO findByPrimaryKey(String follow_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FollowVO followVO = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_one);
			
			stmt.setString(1, follow_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				followVO = new FollowVO();
				followVO.setFollow_id(rs.getString("follow_id"));
				followVO.setMember_id(rs.getString("member_id"));
				followVO.setFollowed_member_id(rs.getString("followed_member_id"));
				followVO.setCreate_time(rs.getTimestamp("create_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		return followVO;
	}

	@Override
	public List<FollowVO> getAll(){
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO followVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_all);
			rs = stmt.executeQuery();
			while(rs.next()) {
				followVO = new FollowVO();
				followVO.setFollow_id(rs.getString("follow_id"));
				followVO.setMember_id(rs.getString("member_id"));
				followVO.setFollowed_member_id(rs.getString("followed_member_id"));
				followVO.setCreate_time(rs.getTimestamp("create_time"));
				list.add(followVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
	public List<FollowVO> getByMemberId(String member_id){
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO followVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_by_member_id);
			
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				followVO = new FollowVO();
				followVO.setFollow_id(rs.getString("follow_id"));
				followVO.setMember_id(rs.getString("member_id"));
				followVO.setFollowed_member_id(rs.getString("followed_member_id"));
				followVO.setCreate_time(rs.getTimestamp("create_time"));
				list.add(followVO);
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
	public List<String> getFollowedMemberIdByMemberId(String member_id) {
		List<String> list = new ArrayList<String>();
		String followed_member_id = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_by_member_id);
			
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				followed_member_id = rs.getString("followed_member_id");
				list.add(followed_member_id);
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
	public List<FollowVO> getAllFollowMe(String member_id){
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO followVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_by_followed_member_id);
			
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				followVO = new FollowVO();
				followVO.setFollow_id(rs.getString("follow_id"));
				followVO.setMember_id(rs.getString("member_id"));
				followVO.setFollowed_member_id(rs.getString("followed_member_id"));
				followVO.setCreate_time(rs.getTimestamp("create_time"));
				list.add(followVO);
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
