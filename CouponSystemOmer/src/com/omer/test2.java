package com.omer;

import com.omer.beans.Category;
import com.omer.beans.Coupon;
import com.omer.dao.CouponsDAO;
import com.omer.dbdao.CouponsDBDAO;
import com.omer.exceptions.AuthenticationsExeption;
import com.omer.exceptions.IDDoesntExistException;
import com.omer.exceptions.NotAllowedException;
import com.omer.facades.ClientType;
import com.omer.facades.CompanyFacade;
import com.omer.facades.CustomerFacade;
import com.omer.facades.LoginManager;
import com.omer.utils.DateUtils;

public class test2 {
	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		CouponsDAO coup = new CouponsDBDAO();
		Coupon coupon1 = new Coupon(1, 2, Category.FOOD, "OnePlusOne", "Buy One Pizza Get One Pizza For Free",
				DateUtils.calcDate(01, 12, 2020), DateUtils.calcDate(01, 12, 2021), 12, 16.50, "OnePlus One");
		Coupon coupon2 = new Coupon(2, 2, Category.FOOD, "Free 3 Topping", "Buy family Pizza Get3 free toppings",
				DateUtils.calcDate(01, 03, 2020), DateUtils.calcDate(01, 05, 2020), 10, 0.50, "Free 3 Topping");

		coup.addCoupon(coupon1);
		coup.addCoupon(coupon2);

//		CompanyFacade companyFacade = null;
//		try {
//			companyFacade = (CompanyFacade) LoginManager.getInstance().login("brooklynBurgercom", "1234",
//					ClientType.Company);
//		} catch (AuthenticationsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		companyFacade.addCoupon(coupon1);;
//		coup.addCouponPurchased(2,3);	
//		System.out.println(coup.getAllCouponsByCustomerID(2));
		System.out.println(coup.getOneCoupon(1));
		System.out.println(coup.getCustomerCoupons(2));
		CustomerFacade customerFacade = null;
		try {
			customerFacade = (CustomerFacade) LoginManager.getInstance().login("yael@gmail.com", "1234",
					ClientType.Customer);
		} catch (AuthenticationsExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		try {
			customerFacade.purchaseCoupon(coupon1);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(customerFacade.getCustomerDetails());

		System.out.println("All customer Coupons - from customer Facade");

		System.out.println(customerFacade.getCustomerCoupons());

		System.out.println("All customer Coupons - from customer Facade with category");

		System.out.println(customerFacade.getCustomerCoupons(Category.FOOD));

	}

}
