package com.omer.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;

public class CompanyFacade extends ClientsFacade {

	private int companyID;

	public CompanyFacade() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addCoupon(Coupon coupon) {
	}
	
	public void updateCoupon(Coupon coupon) {
		
	}
	
	public void deleteCoupon(int couponID) {
		
	}
	
	public List<Coupon> getCompanyCoupons(){
	
	List<Coupon> results = new ArrayList<>();
	return results;
	
	}
	public List<Coupon> getCompanyCoupons(Category category){
		
		List<Coupon> results = new ArrayList<>();
		return results;
		
		}
	public List<Coupon> getCompanyCoupons(double maxPrice){
		
		List<Coupon> results = new ArrayList<>();
		return results;
		
		}
	
	public Company getCustomerDetails() {
		Company companyFromDB = new Company();
		return companyFromDB;

}
	}
