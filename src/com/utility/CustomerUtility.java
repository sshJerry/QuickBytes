package com.utility;

import java.util.List;

import com.main.model.Customer;

public class CustomerUtility {

	public static boolean validateUsername(List<Customer> allCustomers, String customerLoginUsername) {
		boolean isValid = false;
		for(Customer c: allCustomers) {
			if(c.getUsername().equals(customerLoginUsername)) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	public static boolean validatePassword(List<Customer> allCustomers, String customerLoginPassword) {
		boolean isValid = false;
		for(Customer c: allCustomers) {
			if(c.getPassword().equals(customerLoginPassword)) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}
	

}
