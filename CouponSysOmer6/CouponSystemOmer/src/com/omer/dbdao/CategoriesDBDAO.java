package com.omer.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.omer.dao.CategoriesDAO;
import com.omer.beans.Category;
import com.omer.beans.Coupon;
import com.omer.db.ConnectionPool;
import com.omer.utils.DateUtils;

//couponsproject1.categories
public class CategoriesDBDAO implements CategoriesDAO {

	private static final String GET_CATEGORY_ID = "SELECT ID FROM coupon_sys.CATEGORIES where NAME=?;";
	private static final String GET_CATEGORY_NAME = "SELECT NAME FROM coupon_sys.CATEGORIES where ID=?;";
	private static final String ADD_QUERY = "INSERT INTO `coupon_sys`.`CATEGORIES` (`id`, `name`) VALUES (?, ?);";

	public int getCategoryID(Category category) {
		Connection connection = null;
		int ID = 0;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_ID);
			statement.setString(1, category.toString());
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ID = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return ID;
	}

	public Category getCategoryName(int ID) {
		Connection connection = null;
		String category = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_NAME);
			statement.setInt(1, ID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				category = resultSet.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return Category.valueOf(category);
	}

	public void addCategoryTest() {
		Connection conn = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
			statement.setInt(1, 1);
			statement.setString(2, "FOOD");
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
			conn = null;
		}
	}

}
