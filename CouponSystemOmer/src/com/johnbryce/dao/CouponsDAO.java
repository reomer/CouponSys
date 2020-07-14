package com.johnbryce.dao;

import java.util.List;

import com.johnbryce.beans.Coupon;

public interface CouponsDAO {
void addCoupon(Coupon coupon);
void deleteCoupon(int coupunId);
public List<Coupon> getAllCoupons();
public Coupon getOneCoupon(int coupnId);
void addCouponPurchased(int customerId,int couponId);
void deleteCouponPurchased(int customerId,int couponId);
void updateCoupon(Coupon coupun);


}