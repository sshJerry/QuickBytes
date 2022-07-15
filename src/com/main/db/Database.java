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

import com.main.model.Set;

import com.main.model.OrderItem;

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
	 * @parameters i -> an item that has just been created and now is being inserted
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
	 * @parameters i --> the item that is going to be revised name --> the old name
	 * of the item
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
	 * get an existing item by itemId
	 * @param itemId - itemId of the Item data object you want to fetch
	 */
	public Item fetchItem(int itemId) {
		dbConnect();
		String sql = "select * from item where itemId=?;";
		Item item = new Item();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemId);
			ResultSet rst = pstmt.executeQuery();
			rst.next(); 
			
			item = new Item(itemId,rst.getInt("vendorId"),rst.getString("name"),rst.getFloat("price"),rst.getInt("quantity"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return item;
	}

	/*
	 * KEVIN
	 * insert a vendor obj without vendorId into db
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
	
	/*
	 * KEVIN
	 * get all existing vendors
	 */
	public List<Vendor> fetchVendors() {
		dbConnect();
		String sql = "select * from vendor;";
		List<Vendor> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				int vendorId = rst.getInt("vendorId");
				int businessId = rst.getInt("businessId");
				String name = rst.getString("name");
				String username = rst.getString("username");
				String password = rst.getString("password");
				
				Vendor v = new Vendor(vendorId,businessId,name,username,password);
				list.add(v);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	/*
	 * KEVIN
	 * get an existing vendor by vendorId
	 * @param vendorId - vendorId of the Vendor data object you want to fetch
	 */
	public Vendor fetchVendor(int vendorId) {
		dbConnect();
		String sql = "select * from vendor where vendorId=?;";
		Vendor vendor = new Vendor();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vendorId);
			ResultSet rst = pstmt.executeQuery();
			rst.next(); 
			
			vendor = new Vendor(vendorId,rst.getInt("businessId"),rst.getString("name"),rst.getString("username"),rst.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return vendor;
	}
	
	/*
	 * JAY
	 */

	
	/** ORDER */

	/*
	 * KEVIN 
	 * TO BE CALLED WHEN CUSTOMER ADDS THEIR FIRST ITEM FROM A VENDOR
	 * @param order - Order object to be inserted
	 */
	public void addOrder(Order order) {
		dbConnect();		
		String sql="insert into `Order`(totalPrice,status,orderTime,endTime,customerId,vendorId) values (?,?,?,?,?,?);";
		 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, order.getTotalPrice());
			pstmt.setString(2, order.getStatus());
			pstmt.setString(3, order.getOrderTime());
			pstmt.setString(4, order.getEndTime());
			pstmt.setInt(5, order.getCustomerId());
			pstmt.setInt(6, order.getVendorId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbClose();
	}

	/*
	 * KEVIN 
	 * get all existing orders
	 */
	public List<Order> fetchOrders() {
		dbConnect();
		String sql = "select * from `order`;";
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
				int vendorId = rst.getInt("vendorId");
				
				Order o = new Order(orderId,totalPrice,status,orderTime,endTime,customerId,vendorId);
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
	 * KEVIN 
	 * get an existing order by orderId
	 * @param orderId - orderId of the Order data object you want to fetch
	 */
	public Order fetchOrder(int orderId) {
		dbConnect();
		String sql = "select * from `order` where orderId=?;";
		Order order = new Order();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			ResultSet rst = pstmt.executeQuery();
			rst.next(); 
			order = new Order(orderId,rst.getFloat("totalPrice"),rst.getString("status"),rst.getString("orderTime"),rst.getString("endTime"),rst.getInt("customerId"),rst.getInt("vendorId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dbClose();
		return order;
	}
	
	/*
	 * KEVIN
	 * get existing orders by vendorId
	 * @param vendorId - vendorId of the Order data object you want to fetch
	 */
	public List<Order> fetchOrders(int vendorId) {
		dbConnect();
		String sql = "select * from `order` where vendorId=?;";
		List<Order> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vendorId);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				int orderId = rst.getInt("orderId");
				float totalPrice = rst.getFloat("totalPrice");
				String status = rst.getString("status");
				String orderTime = rst.getString("orderTime");
				String endTime = rst.getString("endTime");
				int customerId = rst.getInt("customerId");
				
				Order o = new Order(orderId,totalPrice,status,orderTime,endTime,customerId,vendorId);
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
	 * KEVIN 
	 * update an existing Order with new Order object's attributes
	 * @param order - old Order obj to be updated
	 * @param newOrder - new Order obj whose values we are updating order with
	 */
	public void updateOrder(Order order, Order newOrder) {
		dbConnect();
		String sql = "update `Order` set totalPrice=?, status=?, orderTime=?, endTime=?, customerId=?, vendorId=? where orderId=?;";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, newOrder.getTotalPrice());
			pstmt.setString(2, newOrder.getStatus());
			pstmt.setString(3, newOrder.getOrderTime());
			pstmt.setString(4, newOrder.getEndTime());
			pstmt.setInt(5, newOrder.getCustomerId());
			pstmt.setInt(6, newOrder.getVendorId());
			pstmt.setInt(7, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		String sql="delete from `order` where id=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}
	
	/** ORDER_ITEM */

	/*
	 * KEVIN
	 * get all existing order items
	 */
	public List<OrderItem> fetchOrderItems() {
		dbConnect();
		String sql = "select * from order_item;";
		List<OrderItem> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				int itemId = rst.getInt("itemId");
				int orderId = rst.getInt("orderId");
				
				OrderItem oi = new OrderItem(orderId,itemId);
				list.add(oi);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN A CUSTOMER ADDS ANY ITEM FROM A VENDOR
	 * after addOrder() when applicable
	 * @param order - order from which you use orderId
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

	//jay to validate vendors credentials
	public Boolean validateVendor(String username, String password) throws SQLException {
		dbConnect();
		String sql="select * from vendor";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet  rst = pstmt.executeQuery();
		boolean result=false;
		while(rst.next()) {
			if(username.equals(rst.getString("username"))) {
				if (password.equals(rst.getString("password"))){
					result=true;
				}
			}
				
			}
		 dbClose();
		return result;
	}
//jay fetch id of vendor based on username
	public int fetchVendorID(String username) {
			
			dbConnect();
			String sql="select vendorId from vendor where username=?";
			PreparedStatement pstmt;
			int id=-1;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				ResultSet  rst = pstmt.executeQuery();
					while(rst.next()) {
						id=rst.getInt("vendorId");
			}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				dbClose();
				return id;
		}

	//jay to insert a new set
	public void insertSet(Set set) {
		dbConnect();
		 String sql="insert into set(name, price, available, vendorid)"
		 		+ "values (?,?,?,?)";
		 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, set.getName());
			pstmt.setFloat(2, set.getPrice());
			pstmt.setInt(3, set.getAvailable());
			pstmt.setInt(4, set.getVendorId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 dbClose();
	}

	//jay add item to item set
	public void addItemToItemSet(int itemId, int setId) {
		dbConnect();
		 String sql="insert into item_set(setId, vendorId)"
		 		+ "values (?,?)";
		 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemId);
			pstmt.setInt(2, setId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 dbClose();
		
	}

	//jay fetch item id
	public int fetchItemID(String name, int vendorId) {
		dbConnect();
		String sql="select itemId from Item where name=? and vendorId=?";
		PreparedStatement pstmt;
		int id=-1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, vendorId);
			ResultSet  rst = pstmt.executeQuery();
				while(rst.next()) {
					id=rst.getInt("itemId");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			dbClose();
			return id;
	}

	//jay fetch set id
	public int fetchSetId(String name) {
		dbConnect();
		String sql="select setId from set where name=?";
		PreparedStatement pstmt;
		int id=-1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet  rst = pstmt.executeQuery();
				while(rst.next()) {
					id=rst.getInt("setId");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			dbClose();
			return id;
		
	}

//jay update item set
	public void updateItemSet(Set set) {
		dbConnect();
		 String sql="update set name=?, set price=? , set available= ?";
		 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, set.getName());
			pstmt.setFloat(2, set.getPrice());
			pstmt.setInt(3, set.getAvailable());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 dbClose();
	}
	
	/*
=======
			/*
>>>>>>> d482231518ec690d7fe2a0082252f08e6a12c537
	 * KEVIN
	 * TO BE CALLED WHEN A CUSTOMER DELETES ANY ITEM FROM AN ORDER
	 * before removeOrder() when applicable
	 * @param order - order from which you use orderId
	 * @param item - item from which you use itemId
	 */
	public void removeOrderItem(Order order, Item item) {
		dbConnect();
		String sql="delete from order_item where itemId=? and orderId=? limit 1;";
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
		String sql="delete from order_item where orderId=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();

	}

	/** ADMIN */
	
	/*
	 * JAMES
	 */
	public boolean validateAdmin(String username, String password) {
		dbConnect();
		String sql = "select * from admin where username=? and password=?";
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

}
