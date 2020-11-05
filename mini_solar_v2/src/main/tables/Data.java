package main.tables;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Data {

	public static void getData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("ID" + rs.getInt("id") + " ");
			buffer.append(rs.getTimestamp("time")+ " ");  
			buffer.append(rs.getInt("bluetooth_id") + " ");
			buffer.append(rs.getString("intensity")+ " ");  
			buffer.append(rs.getString("temperature") + " ");  
			

			System.out.println(buffer.toString());
		}

	}
	
}

