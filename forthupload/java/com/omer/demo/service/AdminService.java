package com.omer.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.NotAllowedException;

import lombok.Data;

@Service
public class AdminService extends ClientService {

	public AdminService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws LoginException {
		String adminMail = "admin@admin.com";
		String adminPass = "admin";
		if (email.equals(adminMail) && password.equals(adminPass)) {
			return true;
		}
		throw new LoginException("Customer login denied - Access not allowed - You are not the admin");
	}

	public void addCompany(Company company) throws NotAllowedException {

		List<Company> companies = this.companyDBDAO.getAllCompanies();
		for (Company c : companies) {
			if (c.getName().equals(company.getName()) || c.getEmail().equals(company.getEmail())) {
				throw new NotAllowedException("Company with this email or name is already exists");
			}
		}
		this.companyDBDAO.addCompany(company);
	}

	public void updateCompany(Company company) throws NotAllowedException {
		if (!companyDBDAO.isCompanyExist(companyDBDAO.getOneCompany(company.getId()).getEmail(),
				companyDBDAO.getOneCompany(company.getId()).getPassword())) {
			throw new NotAllowedException("company doesn't exist");
		}
		String companyName = companyDBDAO.getOneCompany(company.getId()).getName();
		if (!companyName.equalsIgnoreCase(company.getName())) {
			throw new NotAllowedException("changing companys name is not allowed");
		}
		companyDBDAO.updateCompany(company);
	}

	public void deleteCompany(int companyId) {
		List<Coupon> allCoupons = this.couponDBDAO.getAllCouponsByCompany(companyId);
		for (Coupon coupon : allCoupons) {

			this.couponDBDAO.deleteCouponPurchasedByCouponID(coupon.getId());

			this.couponDBDAO.deleteCoupon(coupon.getId());
		}

		this.companyDBDAO.deleteCompany(companyId);
	}

	public List<Company> getAllCompanies() {

		return this.companyDBDAO.getAllCompanies();
	}

	public Company getOneCompany(int customerId) {
		return this.companyDBDAO.getOneCompany(customerId);
	}

	public void addCustomer(Customer customer) throws NotAllowedException {

		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer c : customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				throw new NotAllowedException("customer with this email is already exists");
			}
		}
		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		this.customerDBDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerId) {

		this.customerDBDAO.deleteCustomer(customerId);
	}

	public List<Customer> getAllCustomers() {

		return this.customerDBDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerId) {
		return this.customerDBDAO.getOneCustomer(customerId);

	}
}
