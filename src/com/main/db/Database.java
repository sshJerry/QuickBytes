package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.main.model.Order;
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
	
	/** VENDOR */
	
	public void addVendor(Vendor vendor) {
		dbConnect();
		
		String sql="insert into vendor(businessId,name,username,password) values (?,?,?,?);";
		 
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
	
	/** VENDOR */
	
	//TO BE CALLED WHEN CUSTOMER ADDS THEIR FIRST ITEM FROM A VENDOR
	public void addOrder(Order order) {
		dbConnect();
		
		String sql="insert into `Order`(totalPrice,status,orderTime,endTime,customerId) values (?,?,?,?,?);";
		 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, order.getTotalPrice());
			pstmt.setString(2, order.getStatus());
			pstmt.setString(3, order.getOrderTime());
			pstmt.setString(4, order.getEndTime());
			pstmt.setInt(3, order.getCustomerId());
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
	}
}
