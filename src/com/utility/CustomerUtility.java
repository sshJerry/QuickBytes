package com.utility;

import java.util.List;
import java.util.stream.Collectors;

import com.main.model.Customer;

public class CustomerUtility {

	public static boolean validateUsername(List<Customer> allCustomers, String customerLoginUsername) {
		return allCustomers.stream().map(c->c.getUsername()).collect(Collectors.toList()).contains(customerLoginUsername);
		/*boolean isValid = false;
		for(Customer c: allCustomers) {
			if(c.getUsername().equals(customerLoginUsername)) {
				isValid = true;
				break;
			}
		}
		return isValid;*/
	}

	public static boolean validatePassword(List<Customer> allCustomers, String customerLoginPassword) {
		return allCustomers.stream().map(c->c.getPassword()).collect(Collectors.toList()).contains(customerLoginPassword);
	}
	

}
