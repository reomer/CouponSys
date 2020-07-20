package com.omer.facades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Coupon;
import com.omer.beans.Customer;
import com.omer.dao.CategoriesDAO;
import com.omer.dbdao.CouponsDBDAO;
import com.omer.exceptions.AuthenticationsExeption;
import com.omer.exceptions.IDDoesntExistException;
import com.omer.exceptions.NotAllowedException;
import com.omer.beans.Category;


public class CustomerFacade  extends ClientsFacade{
	
	private int customerID;
	
	public CustomerFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws  AuthenticationsExeption{
		if (!this.customersDAO.isCustomerExist(email, password))
			throw new AuthenticationsExeption();
		this.customerID = this.customersDAO.getCustomerID(email, password);
		return true;
	}
	
	public ArrayList<Coupon> getCustomerCoupons(){
		ArrayList<Coupon> results = new ArrayList<>();
		results = this.couponsDAO.getAllCouponsByCustomerID(customerID);
		return results;

		
	}
	//fix the exceptions
	public void purchaseCoupon(Coupon coupon) throws IDDoesntExistException, NotAllowedException {
		if (!this.couponsDAO.isCouponExists(coupon.getId()))
			throw new IDDoesntExistException();
		boolean couponBought = false; 
		ArrayList<Coupon> userCoupons = this.couponsDAO.getAllCouponsByCustomerID(customerID);
		for (Coupon coupon1 : userCoupons) {
			if (coupon1.getId() == coupon.getId())
				couponBought = true;
		}

		if (couponBought == true) {
			throw new NotAllowedException("Can't buy the same coupon twice.");
		}

		if (this.couponsDAO.getOneCoupon(coupon.getId()).getAmount() == 0) { 
			throw new NotAllowedException("Coupon is not avaliable.");
		} else if (this.couponsDAO.getOneCoupon(coupon.getId()).getEndDate() 
				.compareTo(java.sql.Date.valueOf(LocalDate.now())) == 0) {
			throw new NotAllowedException("This coupon expiered today");

		}

		else {
			this.couponsDAO.addCouponPurchased(this.customerID, coupon.getId());
		}
	}
	
	
	
	public ArrayList<Coupon> getCustomerCoupons(Category category){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons = this.couponsDAO.getAllCouponsByCustomerID(this.customerID);
		ArrayList<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory() == category)
				filteredCoupons.add(coupon);
		}
		return filteredCoupons;
	
	}
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons = this.couponsDAO.getAllCouponsByCustomerID(this.customerID);
		ArrayList<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() < maxPrice) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;

}

	public Customer getCustomerDetails() {
		Customer customerFromDB =  this.customersDAO.getOneCustomer(this.customerID);
		customerFromDB.setCoupons(getCustomerCoupons());
		return customerFromDB;
				
	}
}
