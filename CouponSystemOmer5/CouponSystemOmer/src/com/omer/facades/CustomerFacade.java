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
	
	public List<Coupon> getCustomerCoupons(){
		ArrayList<Coupon> results = new ArrayList<Coupon>();
//		results = this.couponsDAO.getAllCouponsByCustomerID(customerID);
		
		return couponsDAO.getCustomerCoupons(customerID);

		
	}
	//fix the exceptions
	public void purchaseCoupon(Coupon coupon) throws IDDoesntExistException, NotAllowedException {
//		if (!this.couponsDAO.isCouponExists(coupon.getId()))
//			throw new IDDoesntExistException();
//		boolean couponBought = false; 
//		ArrayList<Coupon> userCoupons = this.couponsDAO.getAllCouponsByCustomerID(customerID);
//		for (Coupon c : userCoupons) {
//			if (coupon.getId() == c.getId())
//				couponBought = true;
//		}

//		if (couponBought == true) {
//			throw new NotAllowedException("Can't buy the same coupon twice.");
//		}
//
//		if (this.couponsDAO.getOneCoupon(coupon.getId()).getAmount() == 0) { 
//			throw new NotAllowedException("Coupon is not avaliable.");
//		} else if (this.couponsDAO.getOneCoupon(coupon.getId()).getEndDate() 
//				.compareTo(java.sql.Date.valueOf(LocalDate.now())) == 0) {
//			throw new NotAllowedException("This coupon expiered today");
//
//		}
System.out.println(customerID);
System.out.println(coupon.getId());
			this.couponsDAO.addCouponPurchased(customerID, coupon.getId());
			}
	
	
	
	public ArrayList<Coupon> getCustomerCoupons(Category category){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
//		coupons = this.couponsDAO.getAllCouponsByCustomerID(this.customerID);
		ArrayList<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory() == category)
				filteredCoupons.add(coupon);
		}
		return filteredCoupons;
	
	}
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
//		coupons = this.couponsDAO.getAllCouponsByCustomerID(this.customerID);
		ArrayList<Coupon> filteredCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() < maxPrice) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;

}

	public Customer getCustomerDetails() {
		Customer customer = customersDAO.getOneCustomer(this.customerID);
		customer.setCoupons(getCustomerCoupons());
		return customer;
	}
}
