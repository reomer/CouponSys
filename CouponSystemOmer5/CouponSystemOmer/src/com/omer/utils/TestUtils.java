package com.omer.utils;

import java.util.List;

import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.beans.Customer;

public class TestUtils {
	

	public static void drawLine() {
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

	public static void printCouponsTable(List<Coupon> coupons) {
		System.out.println();
		System.out.printf("%3s %10s %15s %15s %17s %11s %11s %7s %7s %10s", "ID", "COMPAY-ID", "CATEGPRY", "TITLE",
				"DESCRIPTION", "START-DATE", "END-DATE", "AMOUNT", "PRICE", "IMAGE");
		System.out.println();
		System.out.println();
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		TestUtils.drawLine();
		for (int i = 0; i < coupons.size(); i++) {
			System.out.printf("%3s %10s %15s %15s %17s %11s %11s %7s %7s %10s", (coupons.get(i)).getId(),
					(coupons.get(i)).getCompanyId(), (coupons.get(i)).getCategory(), (coupons.get(i)).getTitle(),
					(coupons.get(i)).getDescription(), (coupons.get(i)).getStartDate(), (coupons.get(i)).getEndDate(),
					(coupons.get(i)).getAmount(), (coupons.get(i)).getPrice(), (coupons.get(i)).getImage());
			System.out.println();
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printCompaniesTable(List<Company> arrayList) {
		System.out.println();
		System.out.printf("%10s %10s %20s %10s %10s", "ID", "NAME", "EMAIL", "PASSWORD", "COUPONS");
		System.out.println();
		System.out.println();
		TestUtils.drawLine();
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.printf("%10s %10s %20s %10s %10s", (arrayList.get(i)).getId(), (arrayList.get(i)).getName(),
					(arrayList.get(i)).getEmail(), (arrayList.get(i)).getPassword(), (arrayList.get(i)).getCoupons());
			System.out.println();
			System.out.println();
		}
		System.out.println();

	}

	public static void printCustomersTable(List<Customer> customers) {
		System.out.println();
		System.out.printf("%10s %10s %10s %20s %10s %10s", "ID", "FIRST-NAME", "LAST-NAME", "EMAIL", "PASSWORD",
				"COUPONS");
		System.out.println();
		System.out.println();
		TestUtils.drawLine();
		for (int i = 0; i < customers.size(); i++) {
			System.out.printf("%10s %10s %10s %20s %10s %10s", (customers.get(i)).getId(),
					(customers.get(i)).getFirstName(), (customers.get(i)).getLastName(), (customers.get(i)).getEmail(),
					(customers.get(i)).getPassword(), (customers.get(i)).getCoupons());
			System.out.println();
			System.out.println();
		}
	}

}
