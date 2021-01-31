package com.omer.demo.dbdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omer.demo.beans.Coupon;
import com.omer.demo.repo.CouponRepo;
import com.omer.demo.repo.CustomerRepo;

@Repository
public class CouponDBDAO {

	@Autowired
	private CouponRepo couponRepo;

	public void addCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	
	}

	public void updateCoupon(Coupon coupon) {
		couponRepo.saveAndFlush(coupon);

	}

	public void deleteCoupon(int couponId) {
		couponRepo.deleteById(couponId);

	}

	public List<Coupon> getAllCoupons() {

		return couponRepo.findAll();
	}

	public List<Coupon> getAllCouponsByCompany(int companyId) {

		return couponRepo.findByCompanyID(companyId);
	}

	public Coupon getOneCoupon(int couponId) {

		return couponRepo.findById(couponId);
	}

	public void addCouponPurchased(int customerID, int couponID) {

		couponRepo.addCouponPurchesded(customerID, couponID);

	}

	public void deleteCouponPurchased(int customerID, int couponID) {

		couponRepo.deleteCouponPurchased(customerID, couponID);

	}

	public List<Coupon> getExpieredCoupons(Date date) {
		List<Coupon> Coupons = getAllCoupons();
		List<Coupon> exCoupons = new ArrayList<>();

		for (Coupon coupon : Coupons) {
			if (coupon.getEndDate().before(date)) {
				exCoupons.add(coupon);
			}
		}
		return exCoupons;
	}

	public void deleteCouponPurchasedByCouponID(int id) {
		this.couponRepo.deleteCouponPurchasedByCouponID(id);
	}

}
