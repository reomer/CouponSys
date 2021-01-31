package com.omer.demo.repo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	Coupon findById(int couponId);

	Coupon findByTitle(String title);

	List<Coupon> findByCompanyID(int companyID);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO coupon_sys_phase2.customer_coupons (customer_id, coupons_id) VALUES (:customerID, :couponID)", nativeQuery = true)
	void addCouponPurchesded(int customerID, int couponID);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM coupon_sys_phase2.customer_coupons WHERE customer_id=:customerID and coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchased(int customerID, int couponID);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM coupon_sys_phase2.customer_coupons WHERE coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchasedByCouponID(int couponID);

}