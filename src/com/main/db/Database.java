package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//JAMES - ITEM
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
	
	
	/*James
	 * 
	 * @parameters
	 * name --> is the name of an item that the vendor has 
	 * vendorId --> is the vendor identification number
	 * 
	 * the purpose of this function is to validate that the is an item with name "name" that belongs 
	 * to vendorId
	 * 
	 * 
	 * ********this function is no longer used as of the creation of fetchItems********
	 */
	public boolean validateItemOfVendor(String name, int vendorId) {
		boolean yours = false;
		dbConnect();
		String stmt = "select * from item where name = ? and vendorId = ?";
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, name);
			p.setInt(2, vendorId);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				yours = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return yours;
	}
	
	/*James
	 * 
	 * @parameters
	 * i --> the item that is going to be revised
	 * name --> the old name of the item
	 */
	public void updateItem(Item i) {
		dbConnect();
		String stmt = "update item set name = ?, price = ?, quantity = ? where itemId = ?";
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, i.getName());
			p.setFloat(2, i.getPrice());
			p.setInt(3, i.getQuantity());
			p.setInt(4, i.getItemId());
			p.executeUpdate();
			System.out.println("Your item was successfully edited");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}
	
	/*James
	 * 
	 * @parameters
	 * vendorId--> the id of the vendor whose items are being fetched
	 * 
	 * the purpose of this function is to fetch all items that belong to a specific vendor
	 */
	public List<Item> fetchItems(int vendorId) {
		dbConnect();
		String stmt = "select * from item where vendorId = ?";
		List<Item> li = new ArrayList<>();
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setInt(1, vendorId);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				//for each result, add the item to the list of items
				Item i = new Item();
				int itemId = r.getInt(1);
				String name = r.getString(2);
				float price = r.getFloat(3);
				int quantity = r.getInt(4);
				i.setItemId(itemId);
				i.setName(name);
				i.setPrice(price);
				i.setQuantity(quantity);
				li.add(i);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	//Kevin 
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
