package model;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DataOperationRegLog {
	
	
		
		
		public static boolean insertSeller(Seller s) throws SQLException
		{
			Connection con = DataConnection.getConnection();
			String sql = "INSERT INTO seller (first_name , mid_name , last_name , email_id, password , address , city , state , pin) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, s.getFirstName());
			ps.setString(2,s.getMiddleName());
			ps.setString(3, s.getLastName());
			ps.setString(4, s.getEmail_id());
			ps.setString(5, s.getPassword());
			ps.setString(6, s.getAddress());
			ps.setString(7, s.getCity());
			ps.setString(8, s.getPin());
			ps.setString(9, s.getState());
			
			return ps.executeUpdate()>0;
			
		}
		public static boolean deleteSeller(int seller_id) throws SQLException
		{
			
			Connection con = DataConnection.getConnection();
			String sql = "DELETE FROM seller WHERE seller_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, seller_id);
			System.out.print("TRying delete");
			return ps.executeUpdate()>0;
			
		}
		public static boolean insertCustomer(Customer c) throws SQLException
		{
			Connection con = DataConnection.getConnection();
			String sql = "INSERT INTO customer (first_name , mid_name , last_name , email_id, password , address , city , state , pin) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2,c.getMiddleName());
			ps.setString(3, c.getLastName());
			ps.setString(4, c.getEmail_id());
			ps.setString(5, c.getPassword());
			ps.setString(6, c.getAddress());
			ps.setString(7, c.getCity());
			ps.setString(8, c.getPin());
			ps.setString(9, c.getState());
			int f=6; 
			System.out.print("trying to insert"+f);
			System.out.println(ps.executeUpdate());
			System.out.print("trying to insert"+f);
			
			return f>0;
		
			
			
		}
		
	/*	public static boolean deleteSeller(String username) throws SQLException
		{
			Connection con = DataConnection.getConnection();
			String sql = "DELETE FROM signup WHERE username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			return ps.executeUpdate()>0;
			
		}  */
		
		
		public static int validateUser(String email_id , String password) throws SQLException
		{
			Connection con = DataConnection.getConnection();
			String sql = "SELECT * FROM customer WHERE email_id=? && password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email_id);
			ps.setString(2, password);
			System.out.print(email_id);
			System.out.print(password);
			ResultSet rs = ps.executeQuery();
			int c = 0;
			System.out.print(c);
			if(rs == null)
			{
				System.out.print("null");
			}
			while(rs.next())
			{
				c = 5;
				System.out.print(c);
			}
			 sql = "SELECT * FROM seller WHERE email_id=? && password=?";
			 ps = con.prepareStatement(sql);
			 ps.setString(1, email_id);
			 ps.setString(2, password);
			 rs = ps.executeQuery();
			 while(rs.next())
			 {
				c = 10;
				System.out.print(c);
			 }
			 
			
			 
				 return c;
			
			 
			 
			 
			
			
			
			
			
		}
		

	}


