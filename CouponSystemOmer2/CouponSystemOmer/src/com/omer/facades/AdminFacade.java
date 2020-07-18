package com.omer.facades;

import java.util.ArrayList;

import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;

import java.util.ArrayList;
import java.util.List;

public class AdminFacade extends ClientsFacade{
	private int adminID;
	

	public AdminFacade() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addCompany(Company company) {
	}
	
	public void updateCompany(Company company) {
}
	public void deleteCompany(int companyID) {
	}
	
	public List<Company> getAllCompanies(){
		List<Company> results = new ArrayList<>();
		return results;
	}
	
	public Company getOneCompany(int companyID){
		
	
		Company companyFromDB = new Company();
		return companyFromDB;
	}
	
	public void addCustomer(Customer customer) {
	}
	
	public void updateCustomer(Customer customer) {
}
	public void deleteCustomer(int customerID) {
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> results = new ArrayList<>();
		return results;
	}
	
	public Customer getOneCustomer(int customerID){
		
		
		Customer customerFromDB = new Customer();
		return customerFromDB;
	}
	
}
