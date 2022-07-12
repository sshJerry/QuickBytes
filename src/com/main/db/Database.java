package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.main.model.Item;
import com.main.model.Vendor;
import com.main.model.Customer;
import com.main.model.Order;

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
	
	
	/*
	 * @parameters
	 * i -> an item that has just been created and now is being inserted into the database
	 */
	public void addItem(Item i) {
		dbConnect();
		String stmt = "insert into item(vendorId,name,price,quantity)values (?,?,?,?)";
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setInt(1, i.getVendorId());
			p.setString(2, i.getName());
			p.setFloat(3, i.getPrice());
			p.setInt(4, i.getQuantity());
			p.executeUpdate();
			System.out.println("Item successfully added to your menu!!!");
		}catch(SQLException f) {
			f.printStackTrace();
		}
		dbClose();
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
}
