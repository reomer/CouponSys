package com.omer.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.PurchaseCouponException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerID;

	public CustomerService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (!customerDBDAO.isCustomerExist(email, password)) {
			throw new LoginException("Customer login denied - wrong email or password");
		}
		this.customerID = customerDBDAO.getOneCustomerByEmailAndPassword(email, password).getId();
		System.out.println("Customer - successful login");
		return true;
	}

	public List<Coupon> getCustomerCoupons() {
		return this.customerDBDAO.getOneCustomer(customerID).getCoupons();
	}

	public void purchaseCoupon(Coupon coupon) throws PurchaseCouponException {
		List<Coupon> coupons = getCustomerCoupons();
		System.out.println(coupon);
		if (coupons.contains(coupon)) {
//		for (Coupon c : coupons) {
			// if (coupon.getId() == c.getId()) {
			throw new PurchaseCouponException("Already have this Coupon");
		}
//	}}
		if (couponDBDAO.getOneCoupon(coupon.getId()).getAmount() == 0) {
			throw new PurchaseCouponException("Coupon amount is Zero");
		}
		Date d = new Date();
		System.out.println(d);
		System.out.println(couponDBDAO.getOneCoupon(coupon.getId()).getEndDate());
		if (couponDBDAO.getOneCoupon(coupon.getId()).getEndDate().compareTo(d) < 0) {
			throw new PurchaseCouponException("Expired Coupon not Avialable");
		}
		int amount = couponDBDAO.getOneCoupon(coupon.getId()).getAmount();
		coupon.setAmount(amount - 1);
		couponDBDAO.updateCoupon(coupon);
		couponDBDAO.addCouponPurchased(this.customerID, coupon.getId());

	}

	public List<Coupon> getCustomerCoupons(Category category) {

		List<Coupon> myCoupon = this.getCustomerCoupons();
		List<Coupon> filteredCoupons = new ArrayList<>();

		for (Coupon coupon : myCoupon) {
			if (coupon.getCategory().equals(category)) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;
	}

	public List<Coupon> getCustomerCoupons(Double maxPrice) {

		List<Coupon> myCoupon = this.getCustomerCoupons();
		List<Coupon> filteredCoupons = new ArrayList<>();

		for (Coupon coupon : myCoupon) {
			if (coupon.getPrice() <= maxPrice) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;
	}

	public Customer getCustomerDetails() {

		return customerDBDAO.getOneCustomer(this.customerID);
	}

}
