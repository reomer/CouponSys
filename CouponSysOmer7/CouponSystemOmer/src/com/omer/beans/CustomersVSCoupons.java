package com.omer.beans;

public class CustomersVSCoupons {
	private int customerID;
	private int couponID;

	public CustomersVSCoupons() {
	}

	public CustomersVSCoupons(int customerID, int couponID) {
		super();
		this.customerID = customerID;
		this.couponID = couponID;
	}

	@Override
	public String toString() {
		return "CustomersVSCoupons [customerID=" + customerID + ", couponID=" + couponID + "]";
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

}
