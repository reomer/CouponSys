package com.omer.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.service.AdminService;
import com.omer.demo.service.ClientService;
import com.omer.demo.service.CompanyService;
import com.omer.demo.service.CustomerService;

@Service
public class LoginManeger {

	@Autowired
	CompanyService companyService;

	@Autowired
	CustomerService customerService;

	@Autowired
	AdminService adminService;

	private ClientService clientService;

	public ClientService login(String email, String password, ClientsType clientsType) throws LoginException {
		switch (clientsType) {
		case Admin:
			if (adminService.login(email, password)) {
				return adminService;
			}
			break;

		case Company:
			if (companyService.login(email, password)) {
				int compID = companyService.getCompanyID(email, password);
				companyService.setCompanyID(compID);
				return companyService;
			}

			break;

		case Customer:
			if (customerService.login(email, password)) {
				int custID = companyService.getCompanyID(email, password);
				customerService.setCustomerID(custID);
				return customerService;
			}

			break;

		default: {
			throw new LoginException("invalid password or email");
		}
		}
		return null;

	}
}
