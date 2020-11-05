package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import main.tables.Data;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null; // establishing a connection variable
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBConnection.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // rs will be scrollable, will not show changes made by others,
		       																							// but not updatable
			rs = stmt.executeQuery("Select * FROM test");
			
			rs.first(); // moving the cursor to the first row of the database
			
			String sql = "INSERT INTO test (id, time, bluetooth_id, intensity, temperature)" + " VALUES (?, ?, ?, ?, ?)";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentTime = calendar.getTime();
			
			rs.last();
			int last_id = rs.getInt("id")+1;
			
			int id = 1;
			long time = currentTime.getTime();
			int bluetooth_id = 1;
			String intensity = "19";
			String temperature = "20";
			
			pstmt.setInt(1, last_id);
			pstmt.setTimestamp(2, new Timestamp(time));
			pstmt.setInt(3, bluetooth_id);
			pstmt.setString(4, intensity);
			pstmt.setString(5, temperature);
	
			pstmt.execute(); // command to add a row of data to the database
			
			rs.beforeFirst();
			Data.getData(rs);
		
		} catch (SQLException e) {
			System.err.print(e);
		}
		finally {
			if (rs!= null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}	
	}
}

//rs.absolute(3); to move the cursor to the 3rd row
//System.out.println(rs.getString("first_name")); // prints out the first name of the last student
//System.out.println(rs.getRow()); // will print out how many rows in the database

//addData create = new addData();
//create.add(id, time, intensity, temperature);


