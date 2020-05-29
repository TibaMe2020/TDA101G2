package com.store.controller;

import static com.common.Common.*;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/store/ShowImg")
public class DBGifReader extends HttpServlet {

	Connection conn;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			Statement stmt = conn.createStatement();
			String store_id = req.getParameter("store_id");
			String store_image = req.getParameter("store_image");
			ResultSet rs = stmt.executeQuery("SELECT store_image"+store_image+" FROM store WHERE store_id = '" + store_id + "'");
		
			if (rs.next() && rs.getBytes("store_image"+store_image)!=null) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("store_image"+store_image));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		try {
			// JDBC
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, passWord);
			// JNDI
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PetBoxDB");
//			conn = ds.getConnection();
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
//		} catch (NamingException e) {
//			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}