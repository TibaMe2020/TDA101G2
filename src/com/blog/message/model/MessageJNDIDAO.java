package com.blog.message.model;

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

public class MessageJNDIDAO implements MessageDAOInterface {

	private static DataSource dataSource = null;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String insert = "insert into message(message_id, member_id, post_id, message_content) values('MID' || LPAD(MESSAGE_ID_SEQ.NEXTVAL, 5, '0'), ?, ?, ?)";
	private static final String update = "update message set message_content = ? where message_id = ?";
	private static final String delete = "delete from message where message_id = ?";
	private static final String get_one = "select * from message where message_id = ?";
	private static final String get_all = "select * from message order by message_id desc";
	private static final String get_by_member_id = "select * from message where member_id = ?";
	private static final String get_by_post_id = "select * from message where post_id = ?";
	
	@Override
	public void insert(MessageVO messageVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(insert);
			
			stmt.setString(1, messageVO.getMember_id());
			stmt.setString(2, messageVO.getPost_id());
			stmt.setString(3, messageVO.getMessage_content());
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
	public void update(MessageVO messageVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(update);
			
			stmt.setString(1, messageVO.getMessage_content());
			stmt.setString(2, messageVO.getMessage_id());
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
	public void delete(String message_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(delete);
			
			stmt.setString(1, message_id);
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
	public MessageVO findByPrimaryKey(String message_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MessageVO messageVO = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_one);
			
			stmt.setString(1, message_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setPost_id(rs.getString("post_id"));
				messageVO.setMessage_content(rs.getString("message_content"));
				messageVO.setCreate_time(rs.getTimestamp("create_time"));
				messageVO.setUpdate_time(rs.getTimestamp("update_time"));
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
		return messageVO;
	}
	
	@Override
	public List<MessageVO> getAll() {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_all);
			rs = stmt.executeQuery();
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setPost_id(rs.getString("post_id"));
				messageVO.setMessage_content(rs.getString("message_content"));
				messageVO.setCreate_time(rs.getTimestamp("create_time"));
				messageVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(messageVO);
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
	public List<MessageVO> getByMemberId(String member_id) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_by_member_id);
			
			stmt.setString(1, member_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setPost_id(rs.getString("post_id"));
				messageVO.setMessage_content(rs.getString("message_content"));
				messageVO.setCreate_time(rs.getTimestamp("create_time"));
				messageVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(messageVO);
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
	public List<MessageVO> getByPostId(String post_id) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(get_by_post_id);
			
			stmt.setString(1, post_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setPost_id(rs.getString("post_id"));
				messageVO.setMessage_content(rs.getString("message_content"));
				messageVO.setCreate_time(rs.getTimestamp("create_time"));
				messageVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(messageVO);
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
