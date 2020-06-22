package com.product_score.model;

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

public class Score_DAO implements Score_DAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}

	private static final String INSERT_STMT = "INSERT INTO product_score(product_id,member_id,score) VALUES(?,?,?)";
	private static final String GET_ONE_STMT = "SELECT product_score_id, product_id, member_id, score, create_time from product_score WHERE product_score_id=?";
	private static final String GET_ALL_STMT = "SELECT product_score_id, product_id, member_id, score, create_time from product_score ORDER BY product_score_id";;
	private static final String GETBYPRODUCTID="SELECT * from product_score where product_id=?";
	private static final String GET_BY_MEMBER_ID="SELECT * from product_score where member_id= ?";
	private static final String GET_BY_MID_PID="SELECT * from product_score where member_id= ? and product_id= ?";
	
	public Score_VO getByMbIdPId(String member_id, String product_id) {
		Score_VO score_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MID_PID);

			pstmt.setString(1, member_id);
			pstmt.setString(2, product_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				score_VO = new Score_VO();
				score_VO.setProduct_score_id(rs.getString("product_score_id"));
				score_VO.setProduct_id(rs.getString("product_id"));
				score_VO.setMember_id(rs.getString("member_id"));
				score_VO.setScore(rs.getInt("score"));
				score_VO.setCreate_time(rs.getDate("create_time"));

			}

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return score_VO;
	};
	
	public List<Score_VO> getByMemberId(String member_id) {
		List<Score_VO> vos = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMBER_ID);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Score_VO score_VO = new Score_VO();
				score_VO.setProduct_score_id(rs.getString("product_score_id"));
				score_VO.setProduct_id(rs.getString("product_id"));
				score_VO.setMember_id(rs.getString("member_id"));
				score_VO.setScore(rs.getInt("score"));
				score_VO.setCreate_time(rs.getDate("create_time"));
				vos.add(score_VO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return vos;
	}
	
	
	@Override
	public void insert(Score_VO score_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, score_VO.getProduct_id());
			pstmt.setString(2, score_VO.getMember_id());
			pstmt.setInt(3, score_VO.getScore());

			pstmt.executeUpdate();

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// ONE SEARCH
	@Override
	public Score_VO findByPrimaryKey(String Product_score_id) {
		Score_VO score_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Product_score_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				score_VO = new Score_VO();
				score_VO.setProduct_score_id(rs.getString("product_score_id"));
				score_VO.setProduct_id(rs.getString("product_id"));
				score_VO.setMember_id(rs.getString("member_id"));
				score_VO.setScore(rs.getInt("score"));
				score_VO.setCreate_time(rs.getDate("create_time"));

			}

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return score_VO;
	}

	@Override
	public List<Score_VO> getAll() {
		List<Score_VO> list = new ArrayList<Score_VO>();
		Score_VO score_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				score_VO = new Score_VO();
				score_VO.setProduct_score_id(rs.getString("product_score_id"));
				score_VO.setProduct_id(rs.getString("product_id"));
				score_VO.setMember_id(rs.getString("member_id"));
				score_VO.setScore(rs.getInt("score"));
				score_VO.setCreate_time(rs.getDate("create_time"));
				list.add(score_VO);
			}

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Score_VO> getByProductID(String product_id) {
		List<Score_VO> list = new ArrayList<Score_VO>();
		Score_VO score_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYPRODUCTID);
			pstmt.setString(1, product_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				score_VO = new Score_VO();
				score_VO.setProduct_score_id(rs.getString("product_score_id"));
				score_VO.setProduct_id(rs.getString("product_id"));
				score_VO.setMember_id(rs.getString("member_id"));
				score_VO.setScore(rs.getInt("score"));
				score_VO.setCreate_time(rs.getDate("create_time"));
				list.add(score_VO);
			}

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Score_DAO dao = new Score_DAO();
		// INSERT
//		Score_VO Score_insert=new Score_VO();
//		Score_insert.setProduct_id("PI00001");
//		Score_insert.setMember_id("MB00005");
//		Score_insert.setScore(3);
//		dao.insert(Score_insert);
//		System.out.print(Score_insert);

		// SELECT(ONE)
//		Score_VO score_Search = dao.findByPrimaryKey("SCI00001");
//		System.out.println(score_Search.getProduct_score_id() + "," + score_Search.getProduct_id() + ","
//				+ score_Search.getMember_id() + "," + score_Search.getScore() + "," + score_Search.getCreate_time());

		// SELECT(MORE)
		List<Score_VO> list = dao.getAll();
		for (Score_VO all : list) {
			System.out.println(all.getProduct_score_id() + "," + all.getProduct_id() + "," + all.getMember_id() + ","
					+ all.getScore() + "," + all.getCreate_time());

		}

	}



}
