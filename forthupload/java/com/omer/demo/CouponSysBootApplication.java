package com.omer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSysBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponSysBootApplication.class, args);
		System.out.println("END");
	}

}
