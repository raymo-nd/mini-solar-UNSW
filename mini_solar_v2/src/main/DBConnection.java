// new class to add the connection code, best practice

package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String USERNAME = "raymond";
	private static final String PASSWORD = "password";
	private static final String CONN = "jdbc:mysql://localhost/test?serverTimezone=UTC";
	
	public static Connection getConnection () throws SQLException {
		return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}
}
