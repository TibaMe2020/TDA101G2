package com.store.common;

public class Common {

		public final static String url = "jdbc:oracle:thin:@localhost:1521:XE";
		public final static String driver = "oracle.jdbc.driver.OracleDriver";
		// 必須先在Oracle DB上建立BOOKSHOP_JDBC使用者，並授予連線與建立表格權利
		public final static String userId = "PETBOX";
		public final static String passWord = "123456";

}
