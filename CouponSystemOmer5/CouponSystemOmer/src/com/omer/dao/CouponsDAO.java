package com.omer.dao;

import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Coupon;

public interface CouponsDAO {
	
	public boolean isCouponExists(int couponID);
	
	void addCoupon(Coupon coupon);

	void deleteCoupon(int coupunId);

	public ArrayList<Coupon> getAllCoupons();

    public List<Coupon> getCustomerCoupons(int customerID);

	public Coupon getOneCoupon(int coupnId);
	
	public ArrayList<Coupon> getAllCouponsByCompanyID(int companyID);
	
	public ArrayList<Integer> getAllCouponsByCustomerID(int customerID);

	void addCouponPurchased(int customerId, int couponId);

	void deleteCouponPurchased(int customerId, int couponId);

	void updateCoupon(Coupon coupun);

}
