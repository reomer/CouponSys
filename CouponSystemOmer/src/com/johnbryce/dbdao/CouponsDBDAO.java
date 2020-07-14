package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Coupon;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.db.ConnectionPool;
//import com.mysql.cj.protocol.Resultset;

public class CouponsDBDAO implements CouponsDAO {

	private static Connection conn = null;

	private static final String ADD_QUERY = "INSERT INTO `coupon_sys`.`COUPONS` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?,?, ?, ?, ?, ?, ?,?);\n"
			+ "\r\n" + "";
	private static final String UPDATE_QUERY = "UPDATE `coupon_sys`.`COUPONS` SET `title` =?,`description`=?, `start_date`=?, `end_date`=?, `amount`=?, `price`=?, `image`=? WHERE ('id'=?); \r\n"
			+ "";
	private static final String DELETE_QUERY = "DELETE FROM `coupon_sys`.`COUPONS` WHERE ('id'=?);";
	private static final String GET_ALL_QUERY = "SELECT*FROM coupon_sys.COUPONS;";
	private static final String GET_ONE_QUERY = "SELECT*FROM coupon_sys.COUPONS WHERE id=?";

	private static final String ADD_PURCHASE_QUERY = "INSERT INTO `coupon_sys`.`CUSTOMERS_VS_COUPONS`(`customer_id`.`coupon_id`) VALUES (?,?);\r\n";
	private static final String DELETE_PURCHASE_QUERY = "DELETE FROM coupon_sys.CUSTOMERS_VS_COUPONS WHERE customer_id=? AND coupon_id=?";

	public void addCoupon(Coupon coupon) {

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCateory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, (Date) coupon.getStartDate());
			statement.setDate(6, (Date) coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
			conn = null;
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
			statement.setString(1, coupon.getTitle());
			statement.setString(3, coupon.getDescription());
			statement.setDate(3, (Date) coupon.getStartDate());
			statement.setDate(4, (Date) coupon.getEndDate());
			statement.setInt(5, coupon.getAmount());
			statement.setDouble(6, coupon.getPrice());
			statement.setString(7, coupon.getImage());
			statement.setInt(8, coupon.getId());

			statement.executeUpdate();


		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;

	}

	@Override
	public void deleteCoupon(int coupunId) {

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
			statement.setInt(1, coupunId);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;

	}

	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(GET_ALL_QUERY);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCateory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getNString(5));
				coupon.setStartDate(resultSet.getDate(6));
				coupon.setEndDate(resultSet.getDate(7));
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getInt(9));
				coupons.add(coupon);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int coupnId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(GET_ONE_QUERY);
			statement.setInt(1, coupnId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCateory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getNString(5));
				coupon.setStartDate(resultSet.getDate(6));
				coupon.setEndDate(resultSet.getDate(7));
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getInt(9));
				return coupon;

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return null;

	}

	@Override
	public void addCouponPurchased(int customerId, int couponId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(ADD_PURCHASE_QUERY);
			statement.setInt(1, customerId);
			statement.setInt(2, couponId);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;

	}

	@Override
	public void deleteCouponPurchased(int customerId, int couponId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(DELETE_PURCHASE_QUERY);
			statement.setInt(1, customerId);
			statement.setInt(2, couponId);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;

	}

}
