package com.product.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_version.model.Version_DAO;
import com.product_version.model.Version_VO;

public class Product_DAO implements Product_DAO_interface {

	
//	 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "PETBOX";
//	String passwd = "123456";
		private static final String getbyNewdatefood="";
		private static final String getbylowPricefood=" ";
		private static final String getbyhighPricefood="SELECT P.*, PS.SCORE, PV.PRICE FROM PRODUCT P JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID = PS.PRODUCT_ID " + 
				"JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID = PV.PRODUCT_ID where p.product_class = 'food' order by pv.price desc";
		private static final String getbyhighscorefood="SELECT P.*, PS.SCORE, PV.PRICE FROM PRODUCT P JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID = PS.PRODUCT_ID " + 
				"JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID = PV.PRODUCT_ID where p.product_class = 'food' order by ps.score desc";
		
    private static final String All="SELECT * FROM " + 
    		"PRODUCT P " + 
    		"JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID =PS.PRODUCT_ID " + 
    		"JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID=PV.PRODUCT_ID " + 
    		"order by  p.create_time ";
    private static final String getbykeyword="SELECT * FROM " + 
    		"PRODUCT P " + 
    		"JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID =PS.PRODUCT_ID " + 
    		"JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID=PV.PRODUCT_ID " + 
    		"order by  pv.product_version_id "; 
	private static final String INSERT_STMT = "INSERT INTO product (member_id, name, product_class, description, image1, image2, image3, image4, product_state) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE product SET name=?, product_class=?, description=?, image1=?, image2=?, image3=?, image4=?, product_state=? WHERE product_id=? ";
	private static final String DELETE = "DELETE FROM product WHERE product_id=?";
	private static final String GET_ONE_STMT = "SELECT product_id, member_id, name, product_class, description, image1, image2, image3, image4, product_state, update_time, create_time FROM PRODUCT WHERE product_id=?";
	//最新日期
	private static final String GET_ALL_STMT = "SELECT P.*, PS.SCORE, PV.PRICE FROM PRODUCT P  LEFT JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID =PS.PRODUCT_ID JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID=PV.PRODUCT_ID  ORDER BY P.CREATE_TIME DESC";
	//價錢最低
	private static final String LOW_PRICE="SELECT P.*, PS.SCORE, PV.PRICE FROM PRODUCT P JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID =PS.PRODUCT_ID JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID=PV.PRODUCT_ID  ORDER BY PV.PRICE ";
	//價錢最高
	private static final String HIGH_PRICE="SELECT P.*, PS.SCORE, PV.PRICE FROM PRODUCT P JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID =PS.PRODUCT_ID JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID=PV.PRODUCT_ID  ORDER BY PV.PRICE DESC ";
	//最高評分
	private static final String HIGH_SCORE="SELECT P.*, PS.SCORE, PV.PRICE FROM PRODUCT P JOIN PRODUCT_SCORE PS ON P.PRODUCT_ID =PS.PRODUCT_ID JOIN PRODUCT_VERSION PV ON P.PRODUCT_ID=PV.PRODUCT_ID  ORDER BY SCORE DESC ";
	
	private static final String GET_BY_MID = "select * from product where member_id = ?";
	
	private static final String GET_BY_CLASS = "select sum(price*quantity) as total, product_class " + 
			"from product_order_detail pod " + 
			"join product_version pv on pv.product_version_id = pod.product_version_id " + 
			"join product p on p.product_id = pv.product_id " + 
			"group by product_class";
	
	@Override
	public Map<String, String> getByClass() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_CLASS);
			rs = pstmt.executeQuery();
			Map<String, String> map = new HashMap<>();

			while (rs.next()) {
				map.put(rs.getString("product_class"), rs.getString("total"));
			}
			return map;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	};
	
	
	
	@Override
	public void insertWithVersion(Product_VO product_VO, List<Version_VO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
//    		INSERT INTO product (member_id, name, class, description, image1, image2, image3, image4, product_state) VALUES(?,?,?,?,?,?,?,?,?)
    		// 先新增訂單主檔編號
			String cols[] = {"PRODUCT_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, product_VO.getMember_id());
			pstmt.setString(2, product_VO.getName());
			pstmt.setString(3, product_VO.getProduct_class());
			pstmt.setBytes(4, product_VO.getDescription());
			pstmt.setBytes(5, product_VO.getImage1());
			pstmt.setBytes(6, product_VO.getImage2());
			pstmt.setBytes(7, product_VO.getImage3());
			pstmt.setBytes(8, product_VO.getImage4());
			pstmt.setInt(9, product_VO.getProduct_state());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_product_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_product_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_product_id);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增明細
			Version_DAO dao = new Version_DAO();
			for (Version_VO aDetail : list) {
				aDetail.setProduct_id(next_product_id) ;
				dao.insert2(aDetail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	@Override
	public List<Product_VO> getByMId(String member_id) {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				list.add(product_VO);
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
	public void insert(Product_VO product_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, product_VO.getMember_id());
			pstmt.setString(2, product_VO.getName());
			pstmt.setString(3, product_VO.getProduct_class());
			pstmt.setBytes(4, product_VO.getDescription());
			pstmt.setBytes(5, product_VO.getImage1());
			pstmt.setBytes(6, product_VO.getImage2());
			pstmt.setBytes(7, product_VO.getImage3());
			pstmt.setBytes(8, product_VO.getImage4());
			pstmt.setInt(9, product_VO.getProduct_state());

			pstmt.executeUpdate();

		
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

	@Override
	public void update(Product_VO product_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, product_VO.getName());
			pstmt.setString(2, product_VO.getProduct_class());
			pstmt.setBytes(3, product_VO.getDescription());
			pstmt.setBytes(4, product_VO.getImage1());
			pstmt.setBytes(5, product_VO.getImage2());
			pstmt.setBytes(6, product_VO.getImage3());
			pstmt.setBytes(7, product_VO.getImage4());
			pstmt.setInt(8, product_VO.getProduct_state());
			pstmt.setString(9, product_VO.getProduct_id());
			pstmt.executeUpdate();
			

			// Handle any SQL errors
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

	@Override
	public void delete(String Product_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Product_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public Product_VO findByPrimaryKey(String Product_id) {
		Product_VO product_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Product_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("Product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));

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
		return product_VO;
	}

	@Override
	public List<Product_VO> getAll() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				product_VO.setPrice(rs.getInt("price"));
				product_VO.setScore(rs.getDouble("score"));
				list.add(product_VO);
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
	public List<Product_VO> getAll(Map<String, String[]> map) {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from product "
		          + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
		          + "order by product_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				
				list.add(product_VO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Product_VO> highPrice() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(HIGH_PRICE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				product_VO.setPrice(rs.getInt("price"));
				product_VO.setScore(rs.getDouble("score"));
				list.add(product_VO);
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
	public List<Product_VO> lowPrice() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOW_PRICE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				product_VO.setPrice(rs.getInt("price"));
				product_VO.setScore(rs.getDouble("score"));
				list.add(product_VO);
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
	public List<Product_VO> highScore() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(HIGH_SCORE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				product_VO.setPrice(rs.getInt("price"));
				product_VO.setScore(rs.getDouble("score"));
				list.add(product_VO);
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

	public static void main(String[] args) throws IOException {
		Product_DAO dao = new Product_DAO();
		// INSERT INTO
//		byte[] image = null;
//		try {
//			File f = new File("C:\\Users\\88697\\Desktop\\專題AdobeXD\\圖片\\original.png");
//			InputStream in = new FileInputStream(f);
//			image = new byte[in.available()];
//			in.read(image);
//			in.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
//		Product_VO product_Insert = new Product_VO();
//		product_Insert.setMember_id("MB00010");
//		product_Insert.setName("狗狗套餐");
//		product_Insert.setProduct_class("食品");
//		product_Insert.setDescription(null);
//		product_Insert.setImage1(null);
//		product_Insert.setImage2(null);
//		product_Insert.setImage3(null);
//		product_Insert.setImage4(null);
//		product_Insert.setProduct_state(1);
//		dao.insert(product_Insert);
//		System.out.println(product_Insert);

		// UPDATE

//		Product_VO product_Update = new Product_VO();
//		
//		product_Update.setName("測試");
//		product_Update.setProduct_class("商品");
//		product_Update.setDescription(null);
//		product_Update.setImage1(null);
//		product_Update.setImage2(null);
//		product_Update.setImage3(null);
//		product_Update.setImage4(null);
//		product_Update.setProduct_state(5);
//		product_Update.setProduct_id("PI00028");
//		dao.update(product_Update);
//		System.out.println(product_Update);

		// DELETE

//		dao.delete("PI00012");

		// SEARCH(ONE)

		Product_VO product_Search = dao.findByPrimaryKey("PI00010");
		System.out.println(product_Search.getProduct_id() + "," + product_Search.getMember_id() + ","
				+ product_Search.getName() + "," + product_Search.getProduct_class() + "," + product_Search.getDescription()
				+ "," + product_Search.getImage1() + "," + product_Search.getImage2() + "," + product_Search.getImage3()
				+ "," + product_Search.getImage4() + "," + product_Search.getProduct_state() + ","
				+ product_Search.getCreate_time() + ",");

		// SEARCH(MORE)

//		List<Product_VO> list = dao.getAll();
//		for (Product_VO all : list) {
//			System.out.println(all.getProduct_id() + "," + all.getMember_id() + ","
//					+ all.getName() + "," + all.getProduct_class() + "," + all.getDescription()
//					+ "," + all.getImage1() + "," + all.getImage2() + "," + all.getImage3()
//					+ "," + all.getImage4() + "," + all.getProduct_state() + ","
//					+ all.getCreate_time() + "," +all.getPrice());
//		}
		
		List<Product_VO> list = dao.highPrice();
		for (Product_VO all : list) {
			System.out.println(all.getProduct_id() + "," + all.getMember_id() + ","
					+ all.getName() + "," + all.getProduct_class() + "," + all.getDescription()
					+ "," + all.getImage1() + "," + all.getImage2() + "," + all.getImage3()
					+ "," + all.getImage4() + "," + all.getProduct_state() + ","
					+ all.getCreate_time() + "," +all.getPrice());
		}
	}



	@Override
	public List<Product_VO> all() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(All);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				list.add(product_VO);
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
	public List<Product_VO> getbykeyword() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getbykeyword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				list.add(product_VO);
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
	public List<Product_VO> getbyNewdatefood() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Product_VO> getbylowPricefood() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Product_VO> getbyhighPricefood() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getbyhighPricefood);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				product_VO.setPrice(rs.getInt("price"));
				product_VO.setScore(rs.getDouble("score"));
				list.add(product_VO);
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
	public List<Product_VO> getbyhighscorefood() {
		List<Product_VO> list = new ArrayList<Product_VO>();
		Product_VO product_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getbyhighscorefood);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_VO = new Product_VO();
				product_VO.setProduct_id(rs.getString("product_id"));
				product_VO.setMember_id(rs.getString("member_id"));
				product_VO.setName(rs.getString("name"));
				product_VO.setProduct_class(rs.getString("product_class"));
				product_VO.setDescription(rs.getBytes("description"));
				product_VO.setImage1(rs.getBytes("image1"));
				product_VO.setImage2(rs.getBytes("image2"));
				product_VO.setImage3(rs.getBytes("image3"));
				product_VO.setImage4(rs.getBytes("image4"));
				product_VO.setProduct_state(rs.getInt("product_state"));
				product_VO.setCreate_time(rs.getDate("create_time"));
				product_VO.setPrice(rs.getInt("price"));
				product_VO.setScore(rs.getDouble("score"));
				list.add(product_VO);
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

}
