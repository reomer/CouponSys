package com.omer.dao;

import java.util.List;

import com.omer.beans.Company;

public interface CompaniesDAO {
	boolean isCompanyExist(String email, String password);

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(int companyId);

	public List<Company> getAllCompanies();

	public Company getOneCompany(int companyId);

	int getCompanyID(String email, String password);

}
