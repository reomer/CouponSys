package com.omer.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	public Customer findById(int customerId);

	public Customer findByEmail(String email);

	public boolean findByEmailAndPassword(String email, String password);

	public Customer getOneByEmailAndPassword(String email, String password);

}
