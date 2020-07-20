package com.omer.facades;

import com.omer.dao.CompaniesDAO;
import com.omer.dao.CouponsDAO;
import com.omer.dao.CustomersDAO;
import com.omer.dbdao.CompaniesDBDAO;
import com.omer.dbdao.CouponsDBDAO;
import com.omer.dbdao.CustomersDBDAO;
import com.omer.exceptions.AuthenticationsExeption;

public abstract class ClientsFacade {
protected CompaniesDAO companiesDAO = null;
protected CustomersDAO customersDAO = null;
protected CouponsDAO couponsDAO = null;

public ClientsFacade() {
	companiesDAO = new CompaniesDBDAO();
	customersDAO = new CustomersDBDAO();
	couponsDAO = new CouponsDBDAO(); 
	
}
	public abstract boolean login(String email, String password) throws AuthenticationsExeption;
}
