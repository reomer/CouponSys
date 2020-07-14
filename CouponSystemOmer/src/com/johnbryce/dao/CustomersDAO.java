package com.johnbryce.dao;

import java.util.List;

import com.johnbryce.beans.Customer;

public interface CustomersDAO {
	boolean isCustomerExist(String email, String password);
	void addCustomer(Customer customer);
	void upadateCustomer(Customer customer);
	void deleteCustomer(int customerId);
	List<Customer> getAllCustomers();
	Customer getOneCustomer(int CustomerId);
	
	

}
