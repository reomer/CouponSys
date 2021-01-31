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
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.NotAllowedException;
import com.omer.demo.service.AdminService;
import com.omer.demo.service.CustomerService;
import com.omer.demo.utils.DateUtils;
import com.omer.demo.utils.HeadersArtUtils;
import com.omer.demo.utils.PrintStringUtils;
import com.omer.demo.utils.TablesAndLinesUtils;

@Component
@Order(4)
public class AdminServiceTest implements CommandLineRunner {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CouponDBDAO couponDBDAO;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("testing exceptions - login():");
		System.out.println();
		TablesAndLinesUtils.printLine();
		HeadersArtUtils.printAdminFacadeHeader();

		try {
			adminService.login("adm@admin.com", "admin");
		} catch (LoginException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to login with wrong email");
		}
		try {
			adminService.login("admin@admin.com", "pass");
		} catch (LoginException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to login with wrong password");
		}
		TablesAndLinesUtils.printLine();
		adminService.login("admin@admin.com", "admin");

		TablesAndLinesUtils.printCompaniesTable(adminService.getAllCompanies(), "getting all companies before testing");
		TablesAndLinesUtils.printLine();

		System.out.println("testing exceptions - addCompany():");
		System.out.println();

		Company testComp1 = new Company();
		testComp1.setName("BrownHotel");
		testComp1.setEmail("hotel@Hilton.com");
		testComp1.setPassword("paris");
		try {
			adminService.addCompany(testComp1);

		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(),
					"trying to add another company with the same name as BrownHotel");
		}
		Company testComp2 = new Company();
		testComp2.setName("Hilton");
		testComp2.setEmail("shop@BrownHotel.com");
		testComp2.setPassword("paris");
		try {
			adminService.addCompany(testComp2);

		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(),
					"trying to add another company with the same email as Fefsi");
		}
		Company testComp3 = new Company();
		testComp3.setName("Hilton");
		testComp3.setEmail("hotel@Hilton.com");
		testComp3.setPassword("paris");
		try {
			adminService.addCompany(testComp3);

		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(), "");
		}
		TablesAndLinesUtils.printCompaniesTable(adminService.getAllCompanies(),
				"testing addCompany method (Hiltom was added)");

		TablesAndLinesUtils.printLine();
		System.out.println("testing exceptions - updateCompany():");
		System.out.println();

		testComp3.setName("ParisH");

		try {
			adminService.updateCompany(testComp3);
		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to update Hilton Name");
		}
		testComp3.setName("Hilton");
		testComp3.setPassword("updated");
		adminService.updateCompany(testComp3);
		TablesAndLinesUtils.printCompaniesTable(adminService.getAllCompanies(),
				"testing updateCompany method (Hilton - password updated)");

		TablesAndLinesUtils.printCompaniesTable(Arrays.asList(adminService.getOneCompany(testComp3.getId())),
				"testing getOneCompany method (getting Hilton) ");

		TablesAndLinesUtils.printCustomersTable(adminService.getAllCustomers(), "getting all customers before testing");
		TablesAndLinesUtils.printLine();

		System.out.println("testing exceptions - addCustomer():");
		System.out.println();

		Customer testCustomer1 = new Customer();

		testCustomer1.setFirstName("Paris");
		testCustomer1.setLastName("Hilton");
		testCustomer1.setEmail("biggi@g,ail.com");
		testCustomer1.setPassword("pass");

		try {
			adminService.addCustomer(testCustomer1);
		} catch (NotAllowedException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to add another customer with the same email");
		}

		Customer testCustomer2 = new Customer();

		testCustomer2.setFirstName("Parsi");
		testCustomer2.setLastName("Hil");
		testCustomer2.setEmail("PariHil@email.com");
		testCustomer2.setPassword("pass");

		adminService.addCustomer(testCustomer2);

		TablesAndLinesUtils.printCustomersTable(adminService.getAllCustomers(),
				"testing addCustomer method (Parsi Hil has been added)");

		testCustomer2.setFirstName("Snoop");
		testCustomer2.setLastName("Dog");

		adminService.updateCustomer(testCustomer2);
		TablesAndLinesUtils.printCustomersTable(adminService.getAllCustomers(),
				"testing updateCustomer Method (Paris turned too Snoop)");

		TablesAndLinesUtils.printCustomersTable(Arrays.asList(adminService.getOneCustomer(testCustomer2.getId())),
				"testing getOneCustomer method (getting Snoop)");
//

		TablesAndLinesUtils.printCompaniesTable(adminService.getAllCompanies(), "all companies before deleteCompany ");
		TablesAndLinesUtils.printCustomersTable(adminService.getAllCustomers(), "all customers before deleteCompany ");

		adminService.deleteCompany(testComp3.getId());

		TablesAndLinesUtils.printCompaniesTable(adminService.getAllCompanies(),
				"all companies after deleteCompany method (Hilton has been deleted)");

//
//

		TablesAndLinesUtils.printCustomersTable(adminService.getAllCustomers(), "all customers before deleteCustomer ");

		adminService.deleteCustomer(testCustomer2.getId());

		TablesAndLinesUtils.printCustomersTable(adminService.getAllCustomers(),
				"all customers after deleteCompany (Snoop got punked - aka -  has been deleted)");
//
	}
}
