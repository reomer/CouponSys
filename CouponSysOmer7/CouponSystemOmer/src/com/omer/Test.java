package com.omer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.omer.beans.Category;
import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;
import com.omer.dao.CategoriesDAO;
import com.omer.dao.CompaniesDAO;
import com.omer.dao.CouponsDAO;
import com.omer.dao.CustomersDAO;
import com.omer.db.DatabaseManager;
import com.omer.dbdao.CategoriesDBDAO;
import com.omer.dbdao.CompaniesDBDAO;
import com.omer.dbdao.CouponsDBDAO;
import com.omer.dbdao.CouponsDailyJob;
import com.omer.dbdao.CustomersDBDAO;
import com.omer.exceptions.AllreadyExistexception;
import com.omer.exceptions.AuthenticationsExeption;
import com.omer.exceptions.IDDoesntExistException;
import com.omer.exceptions.NotAllowedException;
import com.omer.facades.AdminFacade;
import com.omer.facades.ClientType;
import com.omer.facades.CompanyFacade;
import com.omer.facades.CustomerFacade;
import com.omer.facades.LoginManager;
import com.omer.utils.DateUtils;
import com.omer.utils.TestUtils;

class Test {

	public static void TestAll() {

		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
		CouponsDAO couponsDAO = new CouponsDBDAO();

		System.out.println();
		System.out.println("Admin - Facade - Test");
		TestUtils.drawLine();

		AdminFacade administrator = null;

		try {
			administrator = (AdminFacade) LoginManager.getInstance().login("admin@brooklynPizza.com", "a1d1m1i1n1",
					ClientType.Administrator);
		} catch (AuthenticationsExeption e) {
			e.printStackTrace();
		}

		Company company1 = new Company();
		company1.setId(1);
		company1.setName("BrooklyPizza");
		company1.setEmail("shop@brooklynPizza.com");
		company1.setPassword("1234");

		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setFirstName("Omer");
		customer1.setLastName("Reboh");
		customer1.setEmail("omer@gmail.com");
		customer1.setPassword("1234");

		try {
			administrator.addCompany(company1);
		} catch (AllreadyExistexception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("The COMPANY: BrooklynPizza was added to the system sucssefully.");

		try {
			administrator.addCustomer(customer1);
		} catch (AllreadyExistexception e1) {
			e1.printStackTrace();
		}
		System.out.println("The CUSTOMER: Omer was added to the system sucssefully.");

		// couponsDAO.addCouponPurchased(customersDAO.getCustomerID(customer1.getEmail(),
		// customer1.getPassword()), 1);
		
		System.out.println();
		System.out.println();
		System.out.println(companiesDAO.getAllCompanies());
		System.out.println();
		System.out.println();
		System.out.println(customersDAO.getAllCustomers());

////		try {
////			System.out.println(administrator.getOneCompany(1));
////		} catch (IDDoesntExistException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
////		System.out.println("Get one customer:");
////		try {
////			System.out.println(administrator.getOneCustomer(1));
////		} catch (IDDoesntExistException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	//testing bad request of getOneCompany/getOneCustomer
////		try {
////		System.out.println(administrator.getOneCompany(2));
////	} catch (IDDoesntExistException e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
////	
////	System.out.println("Get one customer:");
////	try {
////		System.out.println(administrator.getOneCustomer(2));
////	} catch (IDDoesntExistException e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
////
		try {
			administrator.deleteCompany(1);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println(companiesDAO.getAllCompanies());

		System.out.println("Deleted company ID 1 and all coupons related.");
		try {
			administrator.deleteCustomer(1);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();

		System.out.println(customersDAO.getAllCustomers());
		System.out.println();
		System.out.println();

		System.out.println("Deleted customer ID 1 and all coupons related.");
		System.out.println();
		System.out.println();

		System.out.println("Get one company:");
		System.out.println();
		System.out.println();

		Company company2 = new Company();
		company2.setId(2);
		company2.setName("BrooklyBurger");
		company2.setEmail("shop@brooklynBurger.com");
		company2.setPassword("1234");

		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setFirstName("Yael");
		customer2.setLastName("Levy");
		customer2.setEmail("yael@gmail.com");
		customer2.setPassword("1234");

		try {
			administrator.addCompany(company2);
		} catch (AllreadyExistexception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		company2.setEmail("newEmail@brooklynBurger.com");

		System.out.println();
		System.out.println();
		System.out.println("The COMPANY #2: BrooklynBurger was added to the system sucssefully.");
		System.out.println(companiesDAO.getAllCompanies());

		try {
			administrator.addCustomer(customer2);
		} catch (AllreadyExistexception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println("The CUSTOMER#2: Yael was added to the system sucssefully.");
		System.out.println();
		System.out.println();
		System.out.println(customersDAO.getAllCustomers());

		try {
			try {
				administrator.updateCompany(company2);
			} catch (NotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println("Updated company AdminTest email to Updated.");
		customer2.setFirstName("newName");
		try {
			administrator.updateCustomer(customer2);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println("Updated customer AdminTest name to Updated.");
		System.out.println();
		System.out.println();
		System.out.println("Company - Facade - Test");
		TestUtils.drawLine();

		CompanyFacade companyFacade = null;
		try {
			companyFacade = (CompanyFacade) LoginManager.getInstance().login("shop@brooklynBurger.com", "1234",
					ClientType.Company);
		} catch (AuthenticationsExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Coupon coupon1 = new Coupon(1, 2, Category.FOOD, "OnePlusOne", "Buy One Pizza Get One Pizza For Free",
				DateUtils.calcDate(01, 3, 2020), DateUtils.calcDate(01, 4, 2021), 12, 16.50, "OnePlus One");
		Coupon coupon2 = new Coupon(2, 2, Category.FOOD, "Free 3 Topping", "Buy family Pizza Get3 free toppings",
				DateUtils.calcDate(01, 03, 2020), DateUtils.calcDate(01, 05, 2020), 10, 0.50, "Free 3 Topping");
		Coupon coupon3 = new Coupon(3, 2, Category.FOOD, "Free 10th Slice",
				"Buy 10 Pizza Slices Get One Pizza For Free", DateUtils.calcDate(01, 11, 2020),
				DateUtils.calcDate(01, 12, 2020), 20, 5.50, "Free 10th Slice");
		Coupon coupon4 = new Coupon(4, 2, Category.VACATION, "WeekEnd4Free", "Buy 3Nights get weekend for free",
				DateUtils.calcDate(01, 11, 2020), DateUtils.calcDate(01, 12, 2020), 20, 5.50, "Free Weekend");

		// couponsDAO.addCoupon(coupon1);

		companyFacade.addCoupon(coupon1);
		companyFacade.addCoupon(coupon2);
		companyFacade.addCoupon(coupon3);
		companyFacade.addCoupon(coupon4);
		System.out.println();
		System.out.println();
		System.out.println("Get company logged in details:");
		System.out.println();
		System.out.println();
		System.out.println(companyFacade.getCompanyDetails());
		System.out.println("Added coupons CompanyTest to AdminTest company.");
		System.out.println();
		System.out.println();
		System.out.println("All coupons of company logged in:");
		System.out.println();
		System.out.println(coupon1);
		System.out.println();
		System.out.println(coupon2);
		System.out.println();
		System.out.println(coupon3);
		System.out.println();
		System.out.println(coupon4);

		System.out.println();
		System.out.println();
		System.out.println("Get all company's coupons from specific category: ");
		System.out.println();
		System.out.println(companyFacade.getCompanyCoupons(Category.FOOD));
		System.out.println();
		System.out.println("Get all company's coupons under price 10 $: ");
		System.out.println();
		System.out.println(companyFacade.getCompanyCoupons(10));
		try {
			companyFacade.deleteCoupon(coupon2.getId());
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Deleted coupon Free 3 Topping from coupons table.");
		coupon1.setPrice(666);
		try {
			companyFacade.updateCoupon(coupon1);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Updated coupon OnePlusOne price to 666.");

		System.out.println();
		System.out.println("Customer - Facade - ");
		TestUtils.drawLine();

		CustomerFacade customerFacade = null;

		try {
			customerFacade = (CustomerFacade) LoginManager.getInstance().login("yael@gmail.com", "1234",
					ClientType.Customer);
		} catch (AuthenticationsExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Customer logged in details:");
		System.out.println();
		System.out.println(customerFacade.getCustomerDetails());
		System.out.println();
		System.out.println("Purchasing coupons: ");

		Coupon coupon5 = new Coupon(5, 2, Category.FOOD, "Free 7th Slice", "Buy 10 Pizza Slices Get One Pizza For Free",
				DateUtils.calcDate(01, 11, 2020), DateUtils.calcDate(01, 12, 2020), 20, 5.50, "Free 10th Slice");

		companyFacade.addCoupon(coupon5);

//		try {
//			customerFacade.purchaseCoupon(coupon1);
//		} catch (IDDoesntExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotAllowedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			customerFacade.purchaseCoupon(coupon5);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// trying to buy a non existing coupon
//		try {
//			customerFacade.purchaseCoupon(coupon2);
//		} catch (IDDoesntExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotAllowedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		System.out.println("All customer coupons:");
		System.out.println();
		System.out.println(customerFacade.getCustomerCoupons());
		System.out.println();
		System.out.println();
//		System.out.println("Get all customer's coupons from vacation category:");
		// System.out.println(customerFacade.getCustomerCoupons(Category.FOOD));
		System.out.println();
		System.out.println("Get all coupons under price 10$ :");
		System.out.println(customerFacade.getCustomerCoupons(10));
//		System.out.println("Customer logged in details after buying coupons:");
//		System.out.println(customerFacade.getCustomerDetails());
//
//		System.out.println();
//		System.out.println();
//		System.out.println("Clean Expiered Coupons");

//		CouponsDailyJob job = new CouponsDailyJob();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println("Today: " );
//		job.start();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		job.pause();
		System.out.println(couponsDAO.getAllCoupons());
		System.out.println();
		System.out.println();
		System.out.println("end");

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		System.out.println("start");

		Class.forName("com.mysql.cj.jdbc.Driver");

		DatabaseManager.createDatabase();

		TestAll();


		// DatabaseManager.dropDatabase();

		System.out.println("end");

	}
}
