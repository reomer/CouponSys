package com.omer.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import com.omer.beans.Coupon;
import com.omer.beans.Customer;

public class CustomerFacade  extends ClientsFacade{
	
	private int customerID;
	
	public CustomerFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return customersDAO.isCustomerExist(email, password);
	}
	
	public List<Coupon> getCustomerCoupons(){
		List<Coupon> results = new ArrayList<>();
		return results;
		
	}
	public void purchaseCoupon(Coupon coupon) {
	
	}
	public List<Coupon> getCustomerCoupons(Category category){
		List<Coupon> results = new ArrayList<>();
		return results;
	}
	public List<Coupon> getCustomerCoupons(double maxPrice){
		List<Coupon> results = new ArrayList<>();
		return results;
}
	public Customer getCustomerDetails() {
		Customer customerFromDB = new Customer();
		return customerFromDB;
				
	}
}
