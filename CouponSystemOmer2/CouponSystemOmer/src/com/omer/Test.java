package com.omer;

import java.sql.Date;
import java.util.List;

import com.omer.beans.Category;
import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;
import com.omer.dao.CompaniesDAO;
import com.omer.dao.CouponsDAO;
import com.omer.dao.CustomersDAO;
import com.omer.db.DatabaseManager;
import com.omer.dbdao.CompaniesDBDAO;
import com.omer.dbdao.CouponsDBDAO;
import com.omer.dbdao.CustomersDBDAO;

class Test{
public static void main(String[] args) throws ClassNotFoundException{

	System.out.println("start");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	DatabaseManager.createDatabase();

	//******CompanyTests:
	
	// isCompanyExist

	//addCompany
	Company company1 = new Company();
		company1.setName("BrooklyPizza");
		company1.setEmail("shop@brooklynPizza.com");
		company1.setPassword("1234");
		
		CompaniesDAO  companiesDAO = new CompaniesDBDAO();
		
		companiesDAO.addCompany(company1);
		
		// getOneCompany
				Company FromDBCompany = companiesDAO.getOneCompany(1) ;
				FromDBCompany.setPassword("1q2w3e4r");
				
		
	// updateCompany
		companiesDAO.addCompany(FromDBCompany);
	//deleteCompany
		companiesDAO.deleteCompany(1);
	//getAllCompanies
		System.out.println(companiesDAO.getAllCompanies());
	
	
	
	//******CustomerTests:
	
	// isCustomerExist
	
	// addCustomer
	
	Customer customer1 = new Customer();
	customer1.setFirstName("Omer");
	customer1.setLastName("Reboh");
	customer1.setEmail("omer@gmail.com");
	customer1.setPassword("1234");
	
	CustomersDAO customersDAO = new CustomersDBDAO();
	customersDAO.addCustomer(customer1);
	
	//getOneCustomer
	Customer FromDBCustomer = customersDAO.getOneCustomer(1);
	// upadateCustomer
		customersDAO.addCustomer(FromDBCustomer);
	//  deleteCustomer
		customersDAO.deleteCustomer(1);

	// getAllCustomers
	System.out.println(customersDAO.getAllCustomers());
	
	//******CouponTests:
	
	// addCoupon
	Coupon coupon1= new Coupon();
	coupon1.setCompanyId(1);
			coupon1.setCateory(Category.FOOD);
			coupon1.setTitle("OnePlusOne");			
			coupon1.setDescription("Buy One Pizza Get One Pizza For Free");
			coupon1.setStartDate(new java.util.Date());
			coupon1.setEndDate(new java.util.Date());
			coupon1.setAmount(12);
			coupon1.setPrice(16.50);
			coupon1.setImage("http:\\brooklynPizza.com");
			
			CouponsDAO couponsDAO = new CouponsDBDAO();
			couponsDAO.addCoupon(coupon1);
			
	// getOneCoupon
		Coupon fromDBCoupon = couponsDAO.getOneCoupon(1);	
		fromDBCoupon.setDescription("Free 3 Toppings");
	//updateCoupon
		couponsDAO.addCoupon(fromDBCoupon);
	
	//	deleteCoupon 
	couponsDAO.deleteCoupon(1);
	//  getAllCoupons
	System.out.println(couponsDAO.getAllCoupons());
	
	//addPurchasedCoupon
	
	//deletePurchasedCoupon
	
	
	
	DatabaseManager.dropDatabase();

	
		System.out.println("end");
	
	}
}
