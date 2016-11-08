package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
	public static Connection getConnection() throws SQLException
	{
		Connection con;
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		String url = "jdbc:mysql://localhost:3306/ecom1";
		String user = "root";
		String password = "";
		
		con = DriverManager.getConnection(url, user, password);
		return con;
		
	}

}
