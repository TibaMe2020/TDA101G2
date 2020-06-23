package com.blog.post.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.blog.post.model.PostService;

public class DBGifReader2 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String post_id = req.getParameter("post_id");
			String count = req.getParameter("count");
			ResultSet rs = stmt.executeQuery(
				//"SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
			    "SELECT POST_IMAGE" + count + " FROM POST WHERE POST_ID = '" + post_id + "'");

			if (rs.next() && rs.getBytes("post_image" + count) != null) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("POST_IMAGE" + count));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/resources/images/null.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/resources/images/img06.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		} finally {
			out.close();
		}
	}

	public void init() throws ServletException {
		try {
			//JNDI寫法
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
			con = ds.getConnection();
			
			//JDBC寫法
//			String driverClass = "oracle.jdbc.driver.OracleDriver";
//			String url = "jdbc:oracle:thin:@localhost:1521:XE";
//			String user = "PETBOX";
//			String password = "123456";
//			
//			try {
//				Class.forName(driverClass);
//				con = DriverManager.getConnection(url, user, password);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}						
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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