package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.main.model.Customer;

public class Database {
	Connection con;
	
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbytesteam","root","Password123");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	// Jerry Implementation
	public void createCustomerAccount(Customer customer) {
		dbConnect();
		String sqlStatment = "insert into customer(employeeId, firstName, lastName, username, password, balance)"
				+ "values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlStatment);
			pstmt.setInt(1, customer.getEmployeeId());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getUsername());
			pstmt.setString(5, customer.getPassword());
			pstmt.setFloat(6, customer.getBalance());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	public List<Customer> getAllCustomers() {
		dbConnect();
		String sqlStatement="select * from customer";
		List<Customer> customerList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlStatement);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				customerList.add(new Customer(
						rst.getInt("customerId"),
						rst.getInt("employeeId"),
						rst.getString("firstName"),
						rst.getString("lastName"),
						rst.getString("username"),
						rst.getString("password"),
						rst.getFloat("balance")
						));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		dbClose();
		return customerList;
	}
	
	
}
