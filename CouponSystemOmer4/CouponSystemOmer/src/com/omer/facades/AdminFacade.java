package com.omer.facades;

import java.util.ArrayList;

import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;
import com.omer.exceptions.AllreadyExistexception;
import com.omer.exceptions.AuthenticationsExeption;
import com.omer.exceptions.IDDoesntExistException;
import com.omer.exceptions.NotAllowedException;

import java.util.ArrayList;
import java.util.List;


public class AdminFacade extends ClientsFacade{
	private int adminID;
	

	public AdminFacade() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean login(String email, String password)throws AuthenticationsExeption {
		if (email == "admin@brooklynPizza.com" && password == "a1d1m1i1n1")
			return true;
		return false;
	}
	
	//to do
	public void addCompany(Company company) throws AllreadyExistexception {
		if (companiesDAO.isCompanyExist(company.getEmail(),company.getPassword()))
			throw new AllreadyExistexception("Email" + company.getEmail() + "already exists");
	
		else
			companiesDAO.addCompany(company);
	}
	

	public void updateCompany(Company company) throws IDDoesntExistException, NotAllowedException {
		if (!this.companiesDAO.isCompanyExistID(company.getId())) {
			throw new IDDoesntExistException();
		
		}
		this.companiesDAO.updateCompany(company);
	}
	//to do
	public void deleteCompany(int companyID) throws IDDoesntExistException {
		if (!this.companiesDAO.isCompanyExistID(companyID)) {
			throw new IDDoesntExistException();
		}
		this.companiesDAO.deleteCompany(companyID);
	
	}
	//to do

	public ArrayList<Company> getALlCompanies() {
		ArrayList<Company> companies = this.companiesDAO.getAllCompanies();
		for (Company company : companies) {
			company.setCoupons(this.couponsDAO.getAllCouponsByCompanyID(company.getId()));
		}
		return companies;
	}
	//to do

	public Company getOneCompany(int companyID) throws IDDoesntExistException{
		if (!this.companiesDAO.isCompanyExist((this.companiesDAO.getOneCompany(companyID)).getEmail(),(this.companiesDAO.getOneCompany(companyID)).getPassword())) {
			throw new IDDoesntExistException();
		}
		Company company = this.companiesDAO.getOneCompany(companyID);
		company.setCoupons(this.couponsDAO.getAllCouponsByCompanyID(companyID));
		return company;
	}
	
	public void addCustomer(Customer customer) throws AllreadyExistexception {
		if (this.customersDAO.isCustomerExist(customer.getEmail(),customer.getPassword())) {
			throw new AllreadyExistexception("This Email" + customer.getEmail() + "already exists");}
		this.customersDAO.addCustomer(customer);
	}
	
	public void updateCustomer(Customer customer) throws IDDoesntExistException {
		if (!this.customersDAO.isCustomerExistID(customer.getId())) {
			throw new IDDoesntExistException();
		}
		this.customersDAO.upadateCustomer(customer);
	}
	public void deleteCustomer(int customerID) throws IDDoesntExistException {
		if (!this.customersDAO.isCustomerExistID(customerID)) {
		throw new IDDoesntExistException();
	}
	this.customersDAO.deleteCustomer(customerID);
	}
	
	//to do
	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> customers = this.customersDAO.getAllCustomers();
		for (Customer customer : customers) {
			customer.setCoupons(this.couponsDAO.getAllCouponsByCustomerID(customer.getId()));
		}
		return customers;

	}
	
	public Customer getOneCustomer(int customerID) throws IDDoesntExistException {
		Customer customer = this.customersDAO.getOneCustomer(customerID);
		customer.setCoupons(this.couponsDAO.getAllCouponsByCustomerID(customerID));
		return customer;
	}
	
}
