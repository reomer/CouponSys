package com.omer.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;


import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;
import com.omer.dao.CompaniesDAO;
import com.omer.dbdao.CompaniesDBDAO;
import com.omer.exceptions.AuthenticationsExeption;

public class CompanyFacade extends ClientsFacade {

	private int companyID;

	public CompanyFacade() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean login(String email, String password) throws AuthenticationsExeption{
		if (!this.companiesDAO.isCompanyExist(email, password))
			throw new AuthenticationsExeption();
		this.companyID = this.companiesDAO.getCompanyID(email, password);
		return true;
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
