package com.omer.demo.job;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.omer.demo.beans.Coupon;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.utils.PrintStringUtils;
import com.omer.demo.utils.TablesAndLinesUtils;

@Component
public class DailyJob {

	@Autowired
	private CouponDBDAO couponDBDAO;

//	@Scheduled(fixedRate = 1000*30)
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)

	public void doTheJob() {
		Date date = new Date();
		List<Coupon> expiredCoupons = couponDBDAO.getExpieredCoupons(date);
		if (expiredCoupons != null || expiredCoupons.size() > 0) {
			TablesAndLinesUtils.printCouponsTable(expiredCoupons, "all expired coupons");
			for (Coupon coupon : expiredCoupons) {
				System.out.println("Daily Task is deleting Coupons");
				couponDBDAO.deleteCoupon(coupon.getId());
				// couponDBDAO.deleteCouponPurchasedByCouponID(coupon.getId());

			}
			TablesAndLinesUtils.printCouponsTable(couponDBDAO.getAllCoupons(), "coupon list without expired coupons");
			System.out.println();
			System.out.println();
		} else {
			System.out.println("No expired Coupons!!!!!!!");
			System.out.println();
			System.out.println();
		}
	}
}

// couponDBDAO.deleteExpiredCoupons();}
//	}
