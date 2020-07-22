package com.omer.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private static final String DELETE_QUERY = "DELETE FROM `coupon_sys`.`COUPONS` WHERE id=?;\n" + 
			"";
	private static final String IS_COUPON_EXIST = "SELECT * FROM coupon_sys.COUPONS WHERE id=?";

	private static final String GET_ALL_QUERY = "SELECT * FROM coupon_sys.COUPONS;";
	private static final String GET_ALL_BY_COMPANY_ID_QUERY = "SELECT * FROM coupon_sys.COUPONS WHERE company_id = ?";
	private static final String GET_ALL_BY_CUSTOMER_ID_QUERY = "SELECT * FROM coupon_sys.CUSTOMER_VS_COUPONS WHERE customer_id = ?";

	private static final String GET_ONE_QUERY = "SELECT * FROM coupon_sys.COUPONS WHERE id=?";
	public static final String GET_CUSTOMER_COUPONS = "SELECT * FROM `coupon_sys`.`COUPONS` WHERE `id`=?";
	private static final String ADD_PURCHASE_QUERY = "INSERT INTO `coupon_sys`.`CUSTOMER_VS_COUPONS` (`customer_id`, `coupon_id`) VALUES (?, ?);\n" + 
			";\n" + 
			"";
	private static final String DELETE_PURCHASE_QUERY = "DELETE FROM `coupon_sys`.`CUSTOMER_VS_COUPONS` WHERE customer_id=? AND coupon_id=?";

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
			return isExist;

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
			statement.setInt(1, coupon.getCategory().ordinal() + 1);
			statement.setString(2, coupon.getTitle());
			statement.setString(3, coupon.getDescription());
			statement.setDate(4,  DateUtils.convertUtilDateToSQL(coupon.getStartDate()));
			statement.setDate(5, DateUtils.convertUtilDateToSQL(coupon.getEndDate()));
			statement.setInt(6, coupon.getAmount());
			statement.setDouble(7, coupon.getPrice());
			statement.setString(8, coupon.getImage());

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
				coupon.setDescription(resultSet.getString(5));
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
				coupon.setDescription(resultSet.getString(5));
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
	
	public ArrayList<Integer> getAllCouponsByCustomerID(int customerID) {
		
		ArrayList<Integer> coupons = new ArrayList<Integer>();
		CategoriesDBDAO categoryDBDAO = new CategoriesDBDAO();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_CUSTOMER_ID_QUERY);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println("testtt");

				coupons.add(resultSet.getInt(2));		
			}
			return coupons;
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return coupons;
	}
	 @Override
	    public List<Coupon> getCustomerCoupons(int customerID) {

	        List<Coupon> customerAllCoupons = new ArrayList<>();
	        Connection connection2 = null;
	        try {
	            connection2 = ConnectionPool.getInstance().getConnection();

	            for (int couponID : getAllCouponsByCustomerID(customerID)) {
	                String sql = GET_CUSTOMER_COUPONS;
	                PreparedStatement preparedStatement = connection2.prepareStatement(sql);
	                preparedStatement.setInt(1, couponID);
	                ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                	Coupon coupon = new Coupon();
	    				coupon.setId(resultSet.getInt(1));
	    				coupon.setCompanyId(resultSet.getInt(2));
	    				coupon.setCateory(Category.values()[resultSet.getInt(3) - 1]);
	    				coupon.setTitle(resultSet.getString(4));
	    				coupon.setDescription(resultSet.getString(5));
	    				coupon.setStartDate(resultSet.getDate(6));
	    				coupon.setEndDate(resultSet.getDate(7));
	    				coupon.setAmount(resultSet.getInt(8));
	    				coupon.setPrice(resultSet.getInt(9));
	                    customerAllCoupons.add(coupon);
	                }


	            }
	            return customerAllCoupons;

	        } catch (InterruptedException | SQLException e) {
	            System.out.println(e.getMessage());

	        } finally {
	            ConnectionPool.getInstance().returnConnection(connection2);
	            connection2 = null;
	        }
	        return null;


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
				coupon.setDescription(resultSet.getString(5));
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
			Coupon coupon = this.getOneCoupon(couponId);
			coupon.setAmount(coupon.getAmount() - 1);// Decrease coupon amount
			this.updateCoupon(coupon);

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
