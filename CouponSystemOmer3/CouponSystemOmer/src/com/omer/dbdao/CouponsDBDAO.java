package com.omer.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Category;
import com.omer.beans.Coupon;
import com.omer.dao.CouponsDAO;
import com.omer.db.ConnectionPool;
import com.omer.utils.DateUtils;

public class CouponsDBDAO implements CouponsDAO {

	private static Connection conn = null;

	private static final String ADD_QUERY = "INSERT INTO `coupon_sys`.`COUPONS` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?,?, ?, ?, ?, ?, ?,?);\n"
			+ "\r\n" + "";
	private static final String UPDATE_QUERY = "UPDATE `coupon_sys`.`COUPONS` SET `title` =?,`description`=?, `start_date`=?, `end_date`=?, `amount`=?, `price`=?, `image`=? WHERE ('id'=?); \r\n"
			+ "";
	private static final String DELETE_QUERY = "DELETE FROM `coupon_sys`.`COUPONS` WHERE ('id'=?);";
	private static final String IS_COUPON_EXIST = "SELECT * FROM coupon_sys.COUPONS where id=?";

	private static final String GET_ALL_QUERY = "SELECT*FROM coupon_sys.COUPONS;";
	private static final String GET_ALL_BY_COMPANY_ID_QUERY = "SELECT * FROM coupon_sys.COUPONS WHERE company_id = ?";
	private static final String GET_ALL_BY_CUSTOMER_ID_QUERY = "SELECT * FROM coupon_sys.COUPONS WHERE company_id = ?";

	private static final String GET_ONE_QUERY = "SELECT*FROM coupon_sys.COUPONS WHERE id=?";

	private static final String ADD_PURCHASE_QUERY = "INSERT INTO `coupon_sys`.`CUSTOMERS_VS_COUPONS`(`customer_id`.`coupon_id`) VALUES (?,?);\r\n";
	private static final String DELETE_PURCHASE_QUERY = "DELETE FROM coupon_sys.CUSTOMERS_VS_COUPONS WHERE customer_id=? AND coupon_id=?";

	public boolean isCouponExists(int couponID) {
		Connection connection = null;
		boolean isExist = false;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(IS_COUPON_EXIST);
			statement.setInt(1, couponID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				isExist = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return isExist;
	}
	
	public void addCoupon(Coupon coupon) {
	//	DateUtils date1 = new DateUtils();
	//	DateUtils date2 = new DateUtils();
		
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5,  DateUtils.convertUtilDateToSQL(coupon.getStartDate()));
			statement.setDate(6,  DateUtils.convertUtilDateToSQL(coupon.getEndDate()));
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
			statement.setDate(3,  DateUtils.convertUtilDateToSQL(coupon.getStartDate()));
			statement.setDate(4, DateUtils.convertUtilDateToSQL(coupon.getEndDate()));
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

	public ArrayList<Coupon> getAllCoupons() {
		//check array list writing
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
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

	public ArrayList<Coupon> getAllCouponsByCompanyID(int companyID) {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		CategoriesDBDAO categoryDBDAO = new CategoriesDBDAO();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_COMPANY_ID_QUERY);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				Category category = categoryDBDAO.getCategoryName(resultSet.getInt(3));
				coupon.setCateory(category);
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getNString(5));
				coupon.setStartDate(resultSet.getDate(6));
				coupon.setEndDate(resultSet.getDate(7));
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getInt(9));
				coupons.add(coupon);		
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return coupons;
	}
	
	public ArrayList<Coupon> getAllCouponsByCustomerID(int customerID) {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		CategoriesDBDAO categoryDBDAO = new CategoriesDBDAO();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_CUSTOMER_ID_QUERY);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				Category category = categoryDBDAO.getCategoryName(resultSet.getInt(3));
				coupon.setCateory(category);
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getNString(5));
				coupon.setStartDate(resultSet.getDate(6));
				coupon.setEndDate(resultSet.getDate(7));
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getInt(9));
				coupons.add(coupon);		
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

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
