package com.omer.facades;

import java.util.ArrayList;
import java.util.List;


import com.omer.beans.Company;
import com.omer.beans.Category;

import com.omer.beans.Coupon;
import com.omer.beans.Customer;
import com.omer.dao.CompaniesDAO;
import com.omer.dbdao.CompaniesDBDAO;
import com.omer.exceptions.AuthenticationsExeption;
import com.omer.exceptions.IDDoesntExistException;

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
		ArrayList<Coupon> coupons = this.couponsDAO.getAllCouponsByCompanyID(this.companyID);
		for (Coupon c : coupons) {
			if (c.getTitle() == coupon.getTitle()) {
				System.out.println("A Coupon with this title already exists in the system");
				return;
			}
		}
		this.couponsDAO.addCoupon(coupon);
	}
	
	public void updateCoupon(Coupon coupon) throws IDDoesntExistException {
		if (!this.couponsDAO.isCouponExists(coupon.getId()))
			throw new IDDoesntExistException();
		this.couponsDAO.updateCoupon(coupon);
	}
	
	//to fix
	public void deleteCoupon(int couponID) throws IDDoesntExistException {
		if (!this.couponsDAO.isCouponExists(couponID))
			throw new IDDoesntExistException();
		this.couponsDAO.deleteCouponPurchased(this.companyID, couponID);
		this.couponsDAO.deleteCoupon(couponID);
	}
	
	
	public List<Coupon> getCompanyCoupons(){
	
	return this.couponsDAO.getAllCouponsByCompanyID(this.companyID);
	}
	
	//to fix

	public List<Coupon> getCompanyCoupons(Category category){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons = this.couponsDAO.getAllCouponsByCompanyID(this.companyID);
		ArrayList<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category) )
				filteredCoupons.add(coupon);
		}
		return filteredCoupons;
	}
	public List<Coupon> getCompanyCoupons(double maxPrice){
		
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons = this.couponsDAO.getAllCouponsByCompanyID(this.companyID);
		ArrayList<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() < maxPrice) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;
		

	}
	
	public Company getCompanyDetails() {
		Company company = this.companiesDAO.getOneCompany(this.companyID);
		company.setCoupons(getCompanyCoupons());
		return company;
	}
	}
