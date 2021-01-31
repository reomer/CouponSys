package com.omer.demo.dbdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.repo.CouponRepo;
import com.omer.demo.repo.CustomerRepo;

@Repository
public class CustomerDBDAO {

	@Autowired
	private CustomerRepo customerRepo;

	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepo.saveAndFlush(customer);
	}

	public void deleteCustomer(int customerId) {
		customerRepo.deleteById(customerId);
	}

	public boolean isCustomerExist(String email, String password) {

		return (customerRepo.getOneByEmailAndPassword(email, password) != null);
	}

	public Customer getOneCustomerByEmailAndPassword(String email, String password) {
		return customerRepo.getOneByEmailAndPassword(email, password);
	}

	public List<Customer> getAllCustomers() {

		return customerRepo.findAll();
	}

	public Customer getOneCustomer(int customerId) {
		return customerRepo.findById(customerId);

	}
}
