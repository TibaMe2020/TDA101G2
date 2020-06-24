package com.blog.follow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FollowJDBCDAO implements FollowDAOInterface{
	String driverClass = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "PETBOX";
	String password = "123456";
	
	private static final String insert = "insert into follow(follow_id, member_id, followed_member_id) values('FID' || LPAD(FOLLOW_ID_SEQ.NEXTVAL, 5, '0'), ?, ?)";
	private static final String delete = "delete from follow where follow_id = ?";
	private static final String get_one = "select * from follow where follow_id = ?";
	private static final String get_all = "select * from follow order by follow_id desc";
	private static final String get_by_member_id = "select * from follow where member_id = ?";
	private static final String get_by_followed_member_id = "select * from follow where followed_member_id = ?";
	
	@Override
	public void insert(FollowVO followVO) {
		Connection conn = null;
		PreparedStatement stmt = null; 
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(insert);
			
			stmt.setString(1, followVO.getMember_id());
			stmt.setString(2, followVO.getFollowed_member_id());
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(delete);
			
			stmt.setString(1, follow_id);
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
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
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
	public List<String> getFollowedMemberIdByMemberId(String member_id) {
		List<String> list = new ArrayList<String>();
		String followed_member_id = null;
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
				followed_member_id = rs.getString("followed_member_id");
				list.add(followed_member_id);
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
	public List<FollowVO> getAllFollowMe(String member_id){
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO followVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
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
	
	public static void main(String[] args) {
		FollowJDBCDAO test = new FollowJDBCDAO();
		//insert
//		FollowVO followVO = new FollowVO();
//		followVO.setMember_id("MB00001");
//		followVO.setFollowed_member_id("MB00002");
//		test.insert(followVO);
		
		//delete
//		test.delete("FID00011");
		
		//select
//		FollowVO followVO2 = test.findByPrimaryKey("FID00010");
//		System.out.println(followVO2.toString());
		
		//getAll
//		List<FollowVO> list = new ArrayList<FollowVO>();
//		list = test.getAll();
//		for(FollowVO followVO : list) {
//			System.out.println(followVO.toString());
//		}		
		
		//getByMemberId
//		List<FollowVO> list = new ArrayList<FollowVO>();
//		list = test.getByMemberId("MB00001");
//		for(FollowVO followVO : list) {
//			System.out.println(followVO.toString());
//		}
		
		//getFollowedMemberIdByMemberId
		List<String> list = new ArrayList<String>();
		list = test.getFollowedMemberIdByMemberId("MB00001");
		for(String followed_member_id : list) {
			System.out.println(followed_member_id);
		}
		
		//getAllFollowMe
//		List<FollowVO> list = new ArrayList<FollowVO>();
//		list = test.getAllFollowMe("MB00001");
//		for(FollowVO followVO : list) {
//			System.out.println(followVO.toString());
//		}
	}
}
