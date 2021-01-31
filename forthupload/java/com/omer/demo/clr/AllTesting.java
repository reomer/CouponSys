package com.omer.demo.clr;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.repo.CouponRepo;
import com.omer.demo.utils.DateUtils;
import com.omer.demo.utils.HeadersArtUtils;
import com.omer.demo.utils.TablesAndLinesUtils;

@Component
@Order(1)
public class AllTesting  implements CommandLineRunner {

	
	@Autowired
	private CompanyDBDAO companyDBDAO;
	@Autowired
	private CustomerDBDAO customerDBDAO;
	@Autowired
	private CouponDBDAO couponDBDAO;
	


	@Override
	public void run(String... args) throws Exception {
	
		
	System.out.println("Starting teting All DBDAO:");
	System.out.println("------------------------------------------------------------------");

	
	HeadersArtUtils.printCompanyDbdaoHeader();

	System.out.println("Company Tests: Upload 4 companies:");
	
	System.out.println("------------------------------------------------------------------");
	Company comp1 = new Company();
	comp1.setName("BrooklynPizza");
	comp1.setEmail("shop@BrooklynPizza.com");
	comp1.setPassword("1234");
	
	companyDBDAO.addCompany(comp1);
	
	Company comp2 = new Company();
	comp2.setName("BrownHotel");
	comp2.setEmail("shop@BrownHotel.com");
	comp2.setPassword("2234");
	
	Company comp3 = new Company();
	comp3.setName("Apple");
	comp3.setEmail("shop@Apple.com");
	comp3.setPassword("3334");
	
	Company comp4 = new Company();
	comp4.setName("Lululemon");
	comp4.setEmail("shop@Lululemon.com");
	comp4.setPassword("LULU");
	
	companyDBDAO.addCompany(comp1);
	
	companyDBDAO.addCompany(comp2);
	
	companyDBDAO.addCompany(comp3);
	
	companyDBDAO.addCompany(comp4);

	comp3.setName("Apple and Co.");
	comp3.setEmail("shop@Apple&Co.com");
	comp3.setPassword("3134");
	
//	System.out.println("Companies Uplouded in a list");
//	System.out.println(companyDBDAO.getAllCompanies());
	
	TablesAndLinesUtils.printCompaniesTable(companyDBDAO.getAllCompanies(),
			"Companies Uplouded in a list");
	
	System.out.println();
	System.out.println("Before Update"+companyDBDAO.getOneCompany(comp3.getId()));
	System.out.println();

	companyDBDAO.updateCompany(comp3);
	
	System.out.println();
	System.out.println("After Update"+companyDBDAO.getOneCompany(comp3.getId()));
	
	companyDBDAO.deleteCompany(comp3.getId());
	
//	System.out.println("After Deleting Apple&Co."+companyDBDAO.getAllCompanies());
	TablesAndLinesUtils.printCompaniesTable(companyDBDAO.getAllCompanies(),
			"After Deleting Apple&Co.");
	
	// Show is companyexist - throw exception because the company dosent exist and ther is no "getid"
	//System.out.println("After Deleting Apple print if Apple Exist:"+companyDBDAO.isCompanyExist(comp3.getEmail(), comp3.getPassword()));

	
	
	System.out.println();
	HeadersArtUtils.printCouponDbdaoHeader();;
	System.out.println("Coupon Tests: Upload 2 coupons:");
	System.out.println("------------------------------------------------------------------");
		Coupon c1 = new Coupon();
		c1.setCompanyID(1);
		c1.setCategory(Category.Food);
		c1.setTitle("BuyOne GetOne");
		c1.setDescription("Buy one family pizza get one for free");
		c1.setPrice(100.00);
		Date date = (DateUtils.convertDate(new Date(2020, 11, 10)));
		c1.setEndDate(date);
		Date date1 = (DateUtils.convertDate(new Date(2020, 05, 10)));
		c1.setStartDate(date1);
		c1.setAmount(10);
		c1.setImage("http://pizza.com");
		
		Coupon c2 = new Coupon();
		c2.setCompanyID(1);
		c2.setCategory(Category.Food);
		c2.setTitle("10th slice for free");
		c2.setDescription("Buy 9 slices get one for free");
		c2.setPrice(150.00);
		Date date4 =(DateUtils.convertDate (new Date(2020, 11, 10)));
		c2.setEndDate(date4);
		Date date5 =(DateUtils.convertDate (new Date(2020, 06, 10)));
		c2.setStartDate(date5);
		c2.setAmount(10);
		c2.setImage("http://pizza.com");

		System.out.println("Coupon Tests: Upload 2 coupons:");
		couponDBDAO.addCoupon(c1);
		couponDBDAO.addCoupon(c2);
		
//		comp1.setCoupons(Arrays.asList(c1,c2));
		
	
		
//		System.out.println("Coupon Tests: getAll -> 2 coupons:");
//		
//		System.out.println(couponDBDAO.getAllCoupons());
//		
		TablesAndLinesUtils.printCouponsTable(couponDBDAO.getAllCoupons(), "Coupon Tests: getAll -> 2 coupons");
		

		c2.setTitle("8th slice for free");
		c2.setDescription("Buy 7 slices get one for free");
		c2.setPrice(30.00);
		
		
		System.out.println("Before Update"+couponDBDAO.getOneCoupon(c2.getId()));
		couponDBDAO.updateCoupon(c2);
		System.out.println("After Update"+couponDBDAO.getOneCoupon(c2.getId()));
		
		Coupon c3 = new Coupon();
		c3.setCompanyID(2);
		c3.setCategory(Category.Vacation);
		c3.setTitle("Weekend + Sunday4free");
		c3.setDescription("Buy Weekend  get the Sunday for free");
		c3.setPrice(550.00);
		Date date6 =(DateUtils.convertDate(new Date(2020, 10, 10)));
		c3.setEndDate(date6);
		Date date7 = (DateUtils.convertDate(new Date(2020, 05, 2)));
		c3.setStartDate(date7);
		c3.setAmount(13);
		c3.setImage("http://hotel.com");
		
		Coupon c4 = new Coupon();
		c4.setCompanyID(2);
		c4.setCategory(Category.Vacation);
		c4.setTitle("Allincluded Weekend");
		c4.setDescription("50% off Allincluded Weekend");
		c4.setPrice(750.00);
		Date date8 =(DateUtils.convertDate(new Date(2020, 11, 10)));
		c4.setEndDate(date8);
		Date date9 = (DateUtils.convertDate(new Date(2020, 02, 10)));
		c4.setStartDate(date9);
		c4.setAmount(5);
		c4.setImage("http://hotel.com");

		Coupon c5 = new Coupon();
		c5.setCompanyID(2);
		c5.setCategory(Category.Vacation);
		c5.setTitle("Breakfast AllDayLong");
		c5.setDescription("Pay Extra 50$ and get Breakfast all Day long to your room");
		c5.setPrice(750.00);
		Date date10 =(DateUtils.convertDate(new Date(2020, 10, 1)));
		c5.setEndDate(date10);
		Date date11 = (DateUtils.convertDate(new Date(2020, 05, 10)));
		c5.setStartDate(date11);
		c5.setAmount(5);
		c5.setImage("http://hotel.com");
		
		couponDBDAO.addCoupon(c3);
		couponDBDAO.addCoupon(c4);
		couponDBDAO.addCoupon(c5);

//		System.out.println("Before Delete"+couponDBDAO.getAllCoupons());
//		couponDBDAO.deleteCoupon(c2.getId());
//		System.out.println("Before Delete"+couponDBDAO.getAllCoupons());

		TablesAndLinesUtils.printCouponsTable(couponDBDAO.getAllCoupons(), "Before Delete");
		couponDBDAO.deleteCoupon(c2.getId());
		TablesAndLinesUtils.printCouponsTable(couponDBDAO.getAllCoupons(), "After Delete");

		
		System.out.println();
		HeadersArtUtils.printCustomerDbdaoHeader();
		System.out.println("Customer Tests: Upload 2 Customers:");
		System.out.println("------------------------------------------------------------------");
	
		Customer customer1= new Customer();
		customer1.setFirstName("Zigi");
		customer1.setLastName("Stardust");
		customer1.setEmail("zigi@g,ail.com");
		customer1.setPassword("1234");
		
		Customer customer2= new Customer();
		customer2.setFirstName("Biggi");
		customer2.setLastName("Notorious");
		customer2.setEmail("biggi@g,ail.com");
		customer2.setPassword("1234");
		
		customerDBDAO.addCustomer(customer1);
		customerDBDAO.addCustomer(customer2);
		
		//System.out.println("Before Update"+customerDBDAO.getAllCustomers());
		
		TablesAndLinesUtils.printCustomersTable(customerDBDAO.getAllCustomers(), "GetAll Customers test");
		
		customer2.setFirstName("B.I.G");
		customer2.setLastName("Notorious");
		customerDBDAO.updateCustomer(customer2);
		customerDBDAO.deleteCustomer(customer1.getId());
//		System.out.println("After Update"+customerDBDAO.getAllCustomers());
		TablesAndLinesUtils.printCustomersTable(customerDBDAO.getAllCustomers(), "After the update");

		
	}

}
