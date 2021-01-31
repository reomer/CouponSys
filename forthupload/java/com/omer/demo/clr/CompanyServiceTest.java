package com.omer.demo.clr;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Coupon;
import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.NotAllowedException;
import com.omer.demo.service.CompanyService;
import com.omer.demo.service.CustomerService;
import com.omer.demo.utils.DateUtils;
import com.omer.demo.utils.HeadersArtUtils;
import com.omer.demo.utils.PrintStringUtils;
import com.omer.demo.utils.TablesAndLinesUtils;

@Component
@Order(3)
public class CompanyServiceTest implements CommandLineRunner {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CouponDBDAO couponDBDAO;

	@Override
	public void run(String... args) throws NotAllowedException, LoginException {

		System.out.println("testing exceptions - login():");
		System.out.println();
		TablesAndLinesUtils.printLine();

		HeadersArtUtils.printCompanyFacadeHeader();

		try {
			companyService.login("shop@broklynPizza.com", "1234");
		} catch (LoginException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to login with wrong email");
		}
		try {
			companyService.login("shop@BrooklynPizza.com", "wrong pass");
		} catch (LoginException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to login with wrong password");
		}
		TablesAndLinesUtils.printLine();
		// companyService.login("shop@BrooklynPizza.com", "1234");
		companyService.login("shop@Lululemon.com", "LULU");

		companyService.setCompanyID(4);

		// getting all company's coupons before testing

		TablesAndLinesUtils.printCouponsTable(companyService.getCompanyCoupons(),
				"getting all companys coupons before testing");

		Coupon c10 = new Coupon();
		c10.setCompanyID(4);
		c10.setCategory(Category.Sport);
		c10.setTitle("BuyOne GetOne");
		c10.setDescription("Buy one Running throsers and get one for free");
		c10.setPrice(99.00);
		Date date13 = (DateUtils.convertDate(new Date(2020, 11, 10)));
		c10.setEndDate(date13);
		Date date14 = (DateUtils.convertDate(new Date(2020, 05, 10)));
		c10.setStartDate(date14);
		c10.setAmount(8);
		c10.setImage("http://lululemon.com");

		Coupon c11 = new Coupon();
		c11.setCompanyID(4);
		c11.setCategory(Category.Sport);
		c11.setTitle("Online 50%");
		c11.setDescription("Buy 100$ credit and shop instore for 150$");
		c11.setPrice(100.00);
		Date date15 = (DateUtils.convertDate(new Date(2020, 8, 10)));
		c11.setEndDate(date15);
		Date date16 = (DateUtils.convertDate(new Date(2020, 05, 10)));
		c11.setStartDate(date16);
		c11.setAmount(8);
		c11.setImage("http://lululemon.com");

		Coupon c12 = new Coupon();
		c12.setCompanyID(4);
		c12.setCategory(Category.Sport);
		c12.setTitle("Online 50%");
		c12.setDescription("Buy 400$ credit and shop instore for 600$");
		c12.setPrice(100.00);
		Date date17 = (DateUtils.convertDate(new Date(2020, 9, 10)));
		c12.setEndDate(date17);
		Date date18 = (DateUtils.convertDate(new Date(2020, 05, 10)));
		c12.setStartDate(date18);
		c12.setAmount(8);
		c12.setImage("http://lululemon.com");

		this.companyService.addCoupon(c10);
		this.companyService.addCoupon(c11);

//					companyService.getCompanyDetails().setCoupons(Arrays.asList(c10,c11));
//					companyService.updateCompany(companyService.getCompanyDetails());
//					

		TablesAndLinesUtils.printLine();
		System.out.println("testing exceptions - addCoupon():");
		System.out.println();

		try {
			companyService.addCoupon(c12);
		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to add another coupon with the same title");
		}

		TablesAndLinesUtils.printCouponsTable(companyService.getCompanyCoupons(),
				"testing addCoupon method - coupons were added");
		TablesAndLinesUtils.printLine();

		try {
			companyService.updateCoupon(c10);
		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to update BuyOne GetOne companyID");
		}
		c10.setCompanyID(4);

		c10.setDescription("updated");

		companyService.updateCoupon(c10);

		TablesAndLinesUtils.printCouponsTable(companyService.getCompanyCoupons(),
				"testing updateCoupon method (sale description updated)");

		TablesAndLinesUtils.printCouponsTable(companyService.getCompanyCoupons(Category.Sport),
				"testing getAllCouponsByCategory method (sport)");

		TablesAndLinesUtils.printCompaniesTable(Arrays.asList(companyService.getCompanyDetails()),
				"testing getCompanyDetails method (getting LuluLemon details)");

		TablesAndLinesUtils.printCouponsTable(companyService.getCompanyCoupons(),
				"testing deleteCoupon method (all Lululemon coupons before deleting)");

		companyService.deleteCoupon(c10);

		TablesAndLinesUtils.printCouponsTable(companyService.getCompanyCoupons(),
				"testing deleteCoupon method (all Lululemon coupons after deleting SALE)");

		TablesAndLinesUtils.printLine();

	}

}