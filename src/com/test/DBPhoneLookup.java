package com.test;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBPhoneLookup extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();

    try {
    	Context ctx = new javax.naming.InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PetBoxDB");
		con = ds.getConnection();
		// 多了連線池的功能
		
      // Create a Statement object
      stmt = con.createStatement();

      // Execute an SQL query, get a ResultSet
      rs = stmt.executeQuery("SELECT * from Store");

      // Display the result set as a list
      out.println("<HTML><HEAD><TITLE>Phonebook</TITLE></HEAD>");
      out.println("<BODY>");
      out.println("<UL>");
      while(rs.next()) {
        out.println("<LI>" + rs.getString(1) + " " + rs.getString(2)+ " " + rs.getString(3)+ " " +rs.getString(4));
      }
      out.println("</UL>");
      out.println("</BODY></HTML>");
    }
    catch(Exception e) {
      out.println("SQLException caught: " + e.getMessage());
    }
    finally {
      // Always close the database connection.
      try {
        if (con != null) con.close();
      }
      catch (SQLException ignored) { }
    }
  }
}
