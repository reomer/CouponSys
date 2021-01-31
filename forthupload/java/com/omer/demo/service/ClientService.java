package com.omer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.security.ClientsType;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyDBDAO companyDBDAO;

	@Autowired
	protected CustomerDBDAO customerDBDAO;

	@Autowired
	protected CouponDBDAO couponDBDAO;

	public ClientService() {
	}

	public abstract boolean login(String email, String password) throws LoginException;

}
