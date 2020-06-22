package com.member.image;

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

@WebServlet("/member/coverImage")
public class CoverImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petbox");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String member_id = req.getParameter("member_id");
		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
					"SELECT blog_cover_image FROM member WHERE member_id = '" + member_id + "'");){
			
			if (rs.next() && rs.getBinaryStream("blog_cover_image") != null) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("blog_cover_image"));
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream is = getServletContext().getResourceAsStream("/front-end/images/circle.jpg");
				byte[] buf = new byte[is.available()];
				is.read(buf);
				out.write(buf);
				is.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			InputStream is = getServletContext().getResourceAsStream("/front-end/images/circle.jpg");
			byte[] buf = new byte[is.available()];
			is.read(buf);
			out.write(buf);
			is.close();
		}
	}
}