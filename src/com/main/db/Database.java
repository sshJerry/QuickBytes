package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.main.model.Item;
import com.main.model.Order;
import com.main.model.Vendor;
import com.main.model.Admin;
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbytesteam", "root", "Password123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** JAMES - ITEM */

	/*
	 * @parameter i -> an item that has just been created and now is being inserted
	 * into the database
	 */
	public void addItem(Item i) {
		dbConnect();
		String stmt = "insert into item(vendorId,name,price,quantity)values (?,?,?,?);";
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setInt(1, i.getVendorId());
			p.setString(2, i.getName());
			p.setFloat(3, i.getPrice());
			p.setInt(4, i.getQuantity());
			p.executeUpdate();
			System.out.println("Item successfully added to your menu!!!");
		} catch (SQLException f) {
			f.printStackTrace();
		}
		dbClose();
	}

	/*
	 * James
	 * 
	 * @parameters name --> is the name of an item that the vendor has vendorId -->
	 * is the vendor identification number
	 * 
	 * the purpose of this function is to validate that the is an item with name
	 * "name" that belongs to vendorId
	 * 
	 * 
	 * ********this function is no longer used as of the creation of
	 * fetchItems********
	 */
	// public boolean validateItemOfVendor(String name, int vendorId) {
	// boolean yours = false;
	// dbConnect();
	// String stmt = "select * from item where name = ? and vendorId = ?;";
	// try {
	// PreparedStatement p = con.prepareStatement(stmt);
	// p.setString(1, name);
	// p.setInt(2, vendorId);
	// ResultSet r = p.executeQuery();
	// if (r.next()) {
	// yours = true;
	// }
	// }catch(SQLException e) {
	// e.printStackTrace();
	// }
	// dbClose();
	// return yours;
	// }

	/*
	 * James
	 * 
	 * @parameter i --> the item that is going to be revised 
	 * @parameter name --> the old nameof the item
	 */
	public void updateItem(Item i) {
		dbConnect();
		String stmt = "update item set name = ?, price = ?, quantity = ? where itemId = ?;";
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, i.getName());
			p.setFloat(2, i.getPrice());
			p.setInt(3, i.getQuantity());
			p.setInt(4, i.getItemId());
			p.executeUpdate();
			System.out.println("Your item was successfully edited");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	/*
	 * James
	 * 
	 * @parameters vendorId--> the id of the vendor whose items are being fetched
	 * 
	 * the purpose of this function is to fetch all items that belong to a specific
	 * vendor
	 */
	public List<Item> fetchItems(int vendorId) {
		dbConnect();
		String stmt = "select * from item where vendorId = ?;";
		List<Item> li = new ArrayList<>();
		try {
			PreparedStatement p = con.prepareStatement(stmt);
			p.setInt(1, vendorId);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				// for each result, add the item to the list of items
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	/*
	 * KEVIN
	 * 
	 * @param vendor - Vendor obj to be inserted
	 */
	public void addVendor(Vendor vendor) {
		dbConnect();

		String sql = "insert into vendor(businessId,name,username,password) values (?,?,?,?);";

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

	/** ORDER */

	/*
	 * KEVIN TO BE CALLED WHEN CUSTOMER ADDS THEIR FIRST ITEM FROM A VENDOR
	 * 
	 * @param order - Order object to be inserted
	 */
	public void addOrder(Order order) {
		dbConnect();

		String sql = "insert into `Order`(totalPrice,status,orderTime,endTime,customerId) values (?,?,?,?,?);";

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
	/*
	 * KEVIN
	 * TO BE CALLED WHEN A CUSTOMER DELETES THE LAST ITEM OF AN ORDER
	 * @param order - order being deleted
	 */
	public void removeOrder(Order order) {
		dbConnect();
		String sql="delete from order where id=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	/*
	 * KEVIN get all existing orders
	 */
	public List<Order> fetchOrders() {
		dbConnect();
		String sql = "select * from order;";
		List<Order> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				int orderId = rst.getInt("orderId");
				float totalPrice = rst.getFloat("totalPrice");
				String status = rst.getString("status");
				String orderTime = rst.getString("orderTime");
				String endTime = rst.getString("endTime");
				int customerId = rst.getInt("customerId");

				Order o = new Order(orderId, totalPrice, status, orderTime, endTime, customerId);
				list.add(o);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dbClose();
		return list;
	}

	/*
	 * KEVIN get an existing order by orderId
	 * 
	 * @param orderId - orderId of the Order data object you want to fetch
	 */
	public Order fetchOrder(int orderId) {
		dbConnect();
		String sql = "select * from order where orderId=?;";
		Order order = new Order();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			ResultSet rst = pstmt.executeQuery();
			rst.next();

			order = new Order(orderId,rst.getFloat("totalPrice"),rst.getString("status"),rst.getString("orderTime"),rst.getString("endTime"),rst.getInt("customerId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dbClose();
		return order;
	}

	/*
	 * KEVIN update an existing Order with new Order object's attributes
	 * 
	 * @param order - old Order obj to be updated
	 * 
	 * @param newOrder - new Order obj whose values we are updating order with
	 */
	public void updateOrder(Order order, Order newOrder) {
		dbConnect();
		String sql = "update `Order` set totalPrice=?, status=?, orderTime=?, endTime=?, customerId=? where orderId=?;";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, newOrder.getTotalPrice());
			pstmt.setString(2, newOrder.getStatus());
			pstmt.setString(3, newOrder.getOrderTime());
			pstmt.setString(4, newOrder.getEndTime());
			pstmt.setInt(5, newOrder.getCustomerId());
			pstmt.setInt(6, order.getOrderId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dbClose();
	}

	/** ORDER_ITEM */

	/*
	 * KEVIN TO BE CALLED WHEN A CUSTOMER ADDS ANY ITEM FROM A VENDOR after
	 * addOrder() when applicable
	 * 
	 * @param order - order from which you use orderId
	 * 
	 * @param item - item from which you use itemId
	 */
	public void addOrderItem(Order order, Item item) {
		dbConnect();

		String sql = "insert into Order_Item(orderId,itemId) values (?,?);";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderId());
			pstmt.setInt(2, item.getItemId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbClose();
	}
			/*
	 * KEVIN
	 * TO BE CALLED WHEN A CUSTOMER DELETES ANY ITEM FROM AN ORDER
	 * before removeOrder() when applicable
	 * @param order - order from which you use orderId
	 * @param item - item from which you use itemId
	 */
	public void removeOrderItem(Order order, Item item) {
		dbConnect();
		String sql="delete from order where itemId=? and orderId=? limit 1;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item.getItemId());
			pstmt.setInt(2, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN A CUSTOMER DELETES ALL ITEMS FROM AN ORDER
	 * before removeOrder() when applicable
	 * @param order - order from which you use orderId
	 */
	public void removeOrderItems(Order order) {
		dbConnect();
		String sql="delete from order where orderId=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	public boolean validateVendor(String username, String password) {
		dbConnect();
		String sql = "select * from vendor where username=? and password=?";
		boolean isPresent = false;
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

	public boolean validateAdmin(String username, String password) {
		System.out.println("1");
		dbConnect();
		String sql = "select * from admin where username=? and password=?";
		boolean isPresent = false;
		System.out.println("2");
		try {
			System.out.println("3");
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
	
	//adding Admin admin to the database
	public void addAdmin(Admin admin) {

		dbConnect();

		String sql = "insert into admin(firstname,lastname,username,password) values (?,?,?,?);";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getFirstName());
			pstmt.setString(2, admin.getLastName());
			pstmt.setString(3, admin.getUsername());
			pstmt.setString(4, admin.getPassword());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	public int validateCustomerAndReturnId(String username, String password) {
		dbConnect();
		String sql = "select * from customers where username=? and password=?";
		boolean isPresent = false;
		System.out.println("2");
		try {
			System.out.println("3");
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rst = pstmt.executeQuery();
			isPresent = rst.next();
			if (isPresent) {
				dbClose();
				return rst.getInt("customerId");
				
			}
			dbClose();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return -1;
	
	}

	public void addCustomer(Customer c) {
		dbConnect();

		String sql = "insert into customer(employeeId,firstname,lastname,username,password,balance) values (?,?,?,?,?,?);";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c.getEmployeeId());
			pstmt.setString(2, c.getFirstName());
			pstmt.setString(3, c.getLastName());
			pstmt.setString(4, c.getUsername());
			pstmt.setString(5, c.getPassword());
			pstmt.setFloat(6, c.getBalance());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}

}
