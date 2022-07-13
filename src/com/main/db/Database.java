package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.main.model.Vendor;

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

	public void addVendor(Vendor vendor) {
		dbConnect();
		
		String sql="insert into vendor(businessId,name,username,password) values (?,?,?,?)";
		 
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
		
		dbClose();
	}
	public boolean validateVendor(String username,String password) {
		dbConnect();
		String sql="select * from vendor where username=? and password=?";
		boolean isPresent =false; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
		 
			ResultSet rst = pstmt.executeQuery();
			isPresent = rst.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 dbClose();
		return isPresent; 
	}
}
