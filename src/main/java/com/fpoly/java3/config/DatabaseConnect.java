package com.fpoly.java3.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "123456aA@";
	private static final String DB_NAME = "java3";

	public static Connection connection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;database=" + DB_NAME + ";encrypt=true;trustServerCertificate=true";
			Connection con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
