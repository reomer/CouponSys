package com.omer.dao;

import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Company;

public interface CompaniesDAO {
	public boolean isCompanyExist(String email, String password);
	
	public boolean isCompanyExistID(int companyID);


	public void addCompany(Company company);

	public void updateCompany(Company company);

	public void deleteCompany(int companyId);

	public ArrayList<Company> getAllCompanies();

	public Company getOneCompany(int companyId);

	int getCompanyID(String email, String password);

}
