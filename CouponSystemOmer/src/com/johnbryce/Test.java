package com.johnbryce;

import java.sql.Date;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Coupon;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.db.DatabaseManager;
import com.johnbryce.dbdao.CouponsDBDAO;

class Test{
public static void main(String[] args) throws ClassNotFoundException{

	System.out.println("start");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	//******CompanyTests:
	
	//******CustomerTests:
	
	
	//******CouponTests:
	// addCoupon
	Coupon coupon1= new Coupon();
	coupon1.setCompanyId(1);
			coupon1.setCateory(Category.FOOD);
			coupon1.setTitle("yoyoyo");
			coupon1.setDescription("bjb");
			coupon1.setStartDate(new java.util.Date());
			coupon1.setEndDate(new java.util.Date());
			coupon1.setAmount(12);
			coupon1.setPrice(12.67);
			coupon1.setImage("http:\\nike.com");
			
			CouponsDAO couponsDAO = new CouponsDBDAO();
			couponsDAO.addCoupon(coupon1);
			
	// getOneCoupon
		Coupon fromDB = couponsDAO.getOneCoupon(1);	
		fromDB.setDescription("yoyoyo2");
	//updateCoupon
				couponsDAO.addCoupon(fromDB);
	
	//	deleteCoupon 
	couponsDAO.deleteCoupon(1);
	//  getAllCoupons
	System.out.println(couponsDAO.getAllCoupons());
	
	//addPurchasedCoupon
	
	//deletePurchasedCoupon
	
	//
	DatabaseManager.dropAndCreateDatabase();
	
	
		System.out.println("end");
	
	}
}
