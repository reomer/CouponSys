package com.omer.dao;

import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Customer;

public interface CustomersDAO {
	public boolean isCustomerExist(String email, String password);
	public boolean isCustomerExistID(int customerID);

	void addCustomer(Customer customer);
	void upadateCustomer(Customer customer);
	void deleteCustomer(int customerId);
	public ArrayList<Customer> getAllCustomers();
	public Customer getOneCustomer(int CustomerId);
	public int getCustomerID(String email, String password);
	
	

}
