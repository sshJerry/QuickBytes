package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	Connection connect;
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DEBUG: Driver Loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbytesteam"
					,"root","Password123");
			System.out.println("DEBUG: Connection Established");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			connect.close();
			System.out.println("DEBUG: Connection Closed");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
