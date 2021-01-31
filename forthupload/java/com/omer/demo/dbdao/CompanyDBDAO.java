package com.omer.demo.dbdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.repo.CompanyRepo;

@Repository
public class CompanyDBDAO {

	@Autowired
	private CompanyRepo companyRepo;

	public void addCompany(Company company) {
		companyRepo.save(company);
	}

	public void updateCompany(Company company) {
		companyRepo.saveAndFlush(company);
	}

	public void deleteCompany(int companyId) {
		companyRepo.deleteById(companyId);
	}

	public boolean isCompanyExist(String email, String password) {
		return (companyRepo.findByEmailAndPassword(email, password) != null);
	}

	public List<Company> getAllCompanies() {

		return companyRepo.findAll();
	}

	public Company getOneCompany(int companyId) {
		return companyRepo.getOne(companyId);

	}

	public Company getOneCompany(String email, String password) {
		return (companyRepo.getOneByEmailAndPassword(email, password));
	}
}
