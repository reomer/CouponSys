package com.omer.dbdao;
import java.util.ArrayList;
import java.util.Date;

import com.omer.beans.Category;
import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.dao.CompaniesDAO;
import com.omer.dao.CouponsDAO;
import com.omer.db.ConnectionPool;

public class CouponsDailyJob extends Thread {

	private static final int sleep = 24 * 60 * 60 * 100; // 24 hours
	private boolean quit;
	private CouponsDAO couponsDBDAO;
	

	public CouponsDailyJob() {
		this.couponsDBDAO = new CouponsDBDAO();
		this.quit = false;
	}

	public void run() {
		while (!quit) {
			System.out.println("Start cleaner");

			ArrayList<Coupon> coupons = couponsDBDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(new Date())) {
					couponsDBDAO.deleteCouponPurchased(coupon.getCompanyId(),coupon.getId());
					couponsDBDAO.deleteCoupon(coupon.getId());
				}
			}

			try {

				System.out.println("Cleaner is going to sleep " + sleep);
				Thread.sleep(sleep);

			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

	public void pause() {
		this.quit = true;
	}

}

