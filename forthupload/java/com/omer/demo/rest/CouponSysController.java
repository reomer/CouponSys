package com.omer.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omer.demo.beans.Company;
import com.omer.demo.repo.CompanyRepo;

@RestController
@RequestMapping("CouponSys")
public class CouponSysController {

	@Autowired
	private CompanyRepo companyRepo;

	@PostMapping("add")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		return new ResponseEntity<String>("game was added :)", HttpStatus.OK);

	}

	@GetMapping("get-all")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<Company>>(companyRepo.findAll(), HttpStatus.OK);
	}

}
