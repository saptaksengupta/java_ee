package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetFromDatabase {
	
	
	public String getS()
	{
		return "you";
	}
	public  List<Seller> getSeller() throws SQLException
	{
		List<Seller> seller = new ArrayList<Seller>();
		String sql = "SELECT seller_id,first_name , mid_name , last_name , email_id , pin FROM seller";
		Connection con = DataConnection.getConnection();
		Statement cs = con.createStatement();
		ResultSet rs = cs.executeQuery(sql);
		while(rs.next())
		{
			Seller s = new Seller();
			s.setId(rs.getInt("seller_id"));
			s.setFirstName(rs.getString("first_name"));
			s.setMiddleName(rs.getString("mid_name"));
			s.setLastName(rs.getString("last_name"));
			s.setEmail_id(rs.getString("email_id"));
			s.setPin(rs.getString("pin"));
			seller.add(s);
		}
		
		
		
		return seller;
		
	}
	public List<Seller> getSellerById(String email_id) throws SQLException      //get product by product id
	{
		List<Seller> seller = new ArrayList<Seller>();
		Connection con = DataConnection.getConnection();
		String sql = "SELECT * FROM seller WHERE email_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, email_id);
		ResultSet rs = ps.executeQuery();
		Seller s = new Seller();
		while(rs.next())
		{
			
			s.setId(rs.getInt("seller_id"));
			s.setFirstName(rs.getString("first_name"));
			s.setMiddleName(rs.getString("mid_name"));
			s.setLastName(rs.getString("last_name"));
			s.setEmail_id(rs.getString("email_id"));
			s.setPassword(rs.getString("password"));
			s.setAddress(rs.getString("address"));
			s.setCity(rs.getString("city"));
			s.setPin(rs.getString("pin"));
			s.setState(rs.getString("state"));
			seller.add(s);
		}
		return seller;
		
		
	}
		public List<Category> getCategory() throws SQLException{
		
		List<Category> category = new ArrayList<Category>();
		String sql = "select * from category";
		Connection con = DataConnection.getConnection();
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			
			Category c = new Category();
			c.setCategoryName(rs.getString("cat_name"));
			c.setCategoryId(rs.getInt("cat_id"));
			
			category.add(c);
			
		}
		return category;
	}
	
	public List<Customer> getCustomer() throws SQLException
	{
		List<Customer> customer = new ArrayList<Customer>();
		String sql = "SELECT cust_id,first_name , mid_name , last_name , email_id , pin FROM customer";
		Connection con = DataConnection.getConnection();
		Statement cs = con.createStatement();
		ResultSet rs = cs.executeQuery(sql);
		while(rs.next())
		{
			Customer c = new Customer();
			c.setId(rs.getInt("cust_id"));
			c.setFirstName(rs.getString("first_name"));
			c.setMiddleName(rs.getString("middle_name"));
			c.setLastName(rs.getString("last_name"));
			c.setEmail_id(rs.getString("email_id"));
			c.setPin(rs.getString("pin"));
			customer.add(c);
		}
		
		
		
		return customer;
		
	}


}
