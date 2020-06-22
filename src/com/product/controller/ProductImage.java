package com.product.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
@WebServlet(name = "img", urlPatterns = {"/Product_Image"})
public class ProductImage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Connection con;
	
	private static DataSource ds;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/png");
		ServletOutputStream out = res.getOutputStream();
		String product_id = req.getParameter("product_id");
		String image =req.getParameter("image");
		try (
				Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE product_id = '" + product_id + "'");
				){
			
			if(rs.next() && new Integer(image) == 0 && rs.getBytes("description") != null) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("description"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
			} else if (new Integer(image) != 0 && rs.getBytes("image"+image) != null) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("image"+image));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				InputStream is = getServletContext().getResourceAsStream("/resources/images/ProductImage/NoResultProduct/NoResultProduct.jpg");
				InputStream is = getServletContext().getResourceAsStream("/resources/images/tomcat.png");
				byte[] buf = new byte[is.available()];
				is.read(buf);
				out.write(buf);
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				is.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			InputStream is = getServletContext().getResourceAsStream("/resources/images/tomcat.png");
//			InputStream is = getServletContext().getResourceAsStream("/resources/images/ProductImage/NoResultProduct/NoResultProduct.jpg");	
			byte[] buf = new byte[is.available()];
			is.read(buf);
			out.write(buf);
			is.close();
		}
	}

	

}