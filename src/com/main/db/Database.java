package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.main.model.Customer;
import com.main.model.Vendor;

public class Database {
	Connection con;
	
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DEBUG: Driver Loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbytesteam"
					,"root","Password123");
			System.out.println("DEBUG: Connection Established");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			con.close();
			System.out.println("DEBUG: Connection Closed");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Customer fetchCustomer(int id) {
		dbConnect();
		String sql = "select * from employee where id = ?";
		Customer customer = new Customer();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rst = pstmt.executeQuery();
			rst.next(); 
			customer = new Customer(rst.getInt("customerId"), rst.getInt("employeeId"), rst.getString("firstName"), rst.getString("lastName"), rst.getString("username"), rst.getString("password"), rst.getFloat("balance"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return customer;
	}

	
	public List<Customer> fetchCustomers() {
		dbConnect();
		String sql = "select * from customer";
		List<Customer> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				int customerId = rst.getInt("customerId");
				int employeeId = rst.getInt("employeeId");
				String firstName = rst.getString("firstName");
				String lastName = rst.getString("lastName");
				String username = rst.getString("username");
				String password = rst.getString("password");
				float balance = rst.getFloat("balance");
				
				Customer c = new Customer(customerId,employeeId,firstName,lastName,username,password,balance);
				list.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	public void deleteCustomer(int customerId) {
		dbConnect();
		String sql = "delete from customer where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
	}


	public Boolean validateCustomer(String username, String password) throws SQLException {
		dbConnect();
		String sql="select * from customer";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet  rst = pstmt.executeQuery();
		boolean result=false;
		while(rst.next()) {
			if(username.equals(rst.getString("username"))) {
				result=true;
			}
				
			}
			
		return result;
	}

	public Boolean validateVendor(String id, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void insertCustomer(Customer customer) {
		 dbConnect();
		 String sql="insert into customer(employeeId, firstname, lastname, username, password, balance) "
		 		+ "values (?,?,?,?,?,?)";
		 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customer.getEmployeeId());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getUsername());
			pstmt.setString(5, customer.getPassword());
			pstmt.setFloat(6, customer.getBalance());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 dbClose();
	}

	public void insertVendor(Vendor vendor) {
		dbConnect();
		 String sql="insert into vendor(businessId, name, username, password)"
		 		+ "values (?,?,?,?)";
		 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vendor.getBusinessId());
			pstmt.setString(2, vendor.getName());
			pstmt.setString(3, vendor.getUsername());
			pstmt.setString(4, vendor.getPassword());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		
	}

	
}
