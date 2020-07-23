package com.omer.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Category;
import com.omer.beans.Company;
import com.omer.beans.Coupon;
import com.omer.dao.CompaniesDAO;
import com.omer.db.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {

	private static Connection conn = null;

	private static final String ADD_QUERY = "INSERT INTO `coupon_sys`.`COMPANIES` (`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_QUERY = "UPDATE `coupon_sys`.`COMPANIES` SET `name`=?, `email`=?,`password`=? WHERE `id`='?';\n"
			+ "";
	private static final String DELETE_QUERY = "DELETE FROM `coupon_sys`.`COMPANIES` WHERE (`ID` = ?);\n" + "\n" + "";
	private static final String IS_COMPANY_EXIST_ID = "SELECT * FROM coupon_sys.COMPANIES WHERE id=?";

	private static final String GET_ONE_COMPANY_BY_ID = "SELECT * FROM coupon_sys.COMPANIES WHERE email= ? AND password= ?";
	private static final String GET_ALL_QUERY = "SELECT*FROM coupon_sys.COMPANIES;";
	private static final String GET_ONE_QUERY = "SELECT*FROM coupon_sys.COMPANIES WHERE id=?";
	private static final String IS_COMPANY_EXIST_QUERY = "SELECT*FROM coupon_sys.COMPANIES WHERE email= ? AND password= ?";

	@Override
	public boolean isCompanyExist(String email, String password) {
		boolean isExist = false;

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(IS_COMPANY_EXIST_QUERY);
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				isExist = true;

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return isExist;
	}

	public boolean isCompanyExistID(int companyID) {
		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(IS_COMPANY_EXIST_ID);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				isExist = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return isExist;
	}

	@Override
	public void addCompany(Company company) {

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
			statement.setInt(1, company.getId());
			statement.setString(2, company.getName());
			statement.setString(3, company.getEmail());
			statement.setString(4, company.getPassword());
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
			conn = null;
		}
	}

	@Override
	public void updateCompany(Company company) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
			conn = null;
		}
	}

	@Override
	public void deleteCompany(int companyId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
			statement.setInt(1, companyId);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;

	}

	@Override
	public ArrayList<Company> getAllCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(GET_ALL_QUERY);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Company company = new Company();

				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setEmail(resultSet.getString(3));
				company.setPassword(resultSet.getString(4));
				companies.add(company);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return companies;
	}

	@Override
	public Company getOneCompany(int companyId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(GET_ONE_QUERY);
			statement.setInt(1, companyId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Company company = new Company();

				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setEmail(resultSet.getString(3));
				company.setPassword(resultSet.getString(4));
				return company;

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return null;
	}

	public int getCompanyID(String email, String password) {

		Connection conn = null;
		int id = 0;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(GET_ONE_COMPANY_BY_ID);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		conn = null;

		return id;
	}
}
