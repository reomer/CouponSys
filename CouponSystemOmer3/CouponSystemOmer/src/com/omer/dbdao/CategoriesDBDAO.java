package com.omer.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.omer.dao.CategoriesDAO;
import com.omer.beans.Category;
import com.omer.db.ConnectionPool;

//couponsproject1.categories
public class CategoriesDBDAO implements CategoriesDAO {

	private static final String GET_CATEGORY_ID = "SELECT ID FROM coupon_sys.CATEGORIES where NAME=?;";
	private static final String getCategoryName = "SELECT NAME FROM coupon_sys.CATEGORIES where ID=?;";

	public int getCategoryID(Category category) {
		Connection connection = null;
		int x = 0;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_ID);
			statement.setString(1, category.toString());
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				x = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return x;
	}

	public Category getCategoryName(int ID) {
		Connection connection = null;
		String category = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = getCategoryName;
			PreparedStatement statement = connection.prepareStatement(sql);
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

}
