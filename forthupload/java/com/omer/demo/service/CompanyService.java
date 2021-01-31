package com.omer.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.NotAllowedException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyID;

	public CompanyService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (!companyDBDAO.isCompanyExist(email, password)) {
			throw new LoginException("Company login denied - wrong email or password");
		}
		return true;
	}

	public int getCompanyID(String email, String password) {
		return this.companyDBDAO.getOneCompany(email, password).getId();
	}

	public void addCoupon(Coupon coupon) throws NotAllowedException {
		List<Coupon> coupons = this.couponDBDAO.getAllCouponsByCompany(coupon.getCompanyID());
		for (Coupon coup : coupons) {
			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new NotAllowedException("Already exist title ");
			}

		}
		this.couponDBDAO.addCoupon(coupon);

	}

	public void updateCoupon(Coupon coupon) throws NotAllowedException {

		if ((coupon.getId() != this.couponDBDAO.getOneCoupon(coupon.getId()).getId())) {
			throw new NotAllowedException("Cant Update ID");
		}
		if ((coupon.getCompanyID() != this.couponDBDAO.getOneCoupon(coupon.getId()).getCompanyID())) {
			throw new NotAllowedException("Cant Update CompanyID");
		}

		this.couponDBDAO.updateCoupon(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		this.couponDBDAO.deleteCouponPurchasedByCouponID(coupon.getId());
		this.couponDBDAO.deleteCoupon(coupon.getId());
	}

	public List<Coupon> getCompanyCoupons() {
		return this.couponDBDAO.getAllCouponsByCompany(this.companyID);
	}

	public List<Coupon> getCompanyCoupons(Category category) {

		List<Coupon> myCoupon = this.getCompanyCoupons();
		List<Coupon> filteredCoupons = new ArrayList<>();

		for (Coupon coupon : myCoupon) {
			if (coupon.getCategory().equals(category)) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;
	}

	public List<Coupon> getCompanyCoupons(Double maxPrice) {

		List<Coupon> myCoupon = this.getCompanyCoupons();
		List<Coupon> filteredCoupons = new ArrayList<>();

		for (Coupon coupon : myCoupon) {
			if (coupon.getPrice() <= maxPrice) {
				filteredCoupons.add(coupon);
			}
		}
		return filteredCoupons;
	}

	public Company getCompanyDetails() {
		Company company = companyDBDAO.getOneCompany(this.companyID);
		company.setCoupons(getCompanyCoupons());
		return company;

	}

	public void updateCompany(Company company) {
		this.companyDBDAO.updateCompany(company);
	}

}