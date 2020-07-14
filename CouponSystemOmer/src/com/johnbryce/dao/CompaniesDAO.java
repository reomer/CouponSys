package com.johnbryce.dao;

import java.util.List;

import com.johnbryce.beans.Company;

public interface CompaniesDAO {
 boolean isCompanyExist(String email, String password);
 void addCompany(Company company);
 void updateCompany(Company company);
 void deleteCompany(int companyId);
 List<Company> getAllCompanies();
 Company getOneCompany(int companyId);
 
}
