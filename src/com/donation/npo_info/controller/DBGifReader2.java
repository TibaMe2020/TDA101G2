package com.donation.npo_info.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader2 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
       
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String npo_id =req.getParameter("npo_id");
			ResultSet rs = stmt.executeQuery(
				"SELECT npo_image FROM npo_info WHERE npo_id ='"+ npo_id +"' ");

			if (rs.next() && rs.getBytes("npo_image") != null) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("npo_image"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/resources/images/null.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/resources/images/null.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
//		
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String userid = "PETBOX";
//		String passwd ="123456";
//			try {
//				try {
//					Class.forName(driver);
//					con = DriverManager.getConnection(url,userid,passwd);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Context ctx = new javax.naming.InitialContext();
//				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PETBOX");
//				 con = ds.getConnection();
//			} catch (NamingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
			 con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}