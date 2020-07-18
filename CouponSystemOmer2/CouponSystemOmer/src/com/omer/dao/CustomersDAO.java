package com.omer.dao;

import java.util.List;

import com.omer.beans.Customer;

public interface CustomersDAO {
	boolean isCustomerExist(String email, String password);
	void addCustomer(Customer customer);
	void upadateCustomer(Customer customer);
	void deleteCustomer(int customerId);
	public List<Customer> getAllCustomers();
	public Customer getOneCustomer(int CustomerId);
	
	

}
