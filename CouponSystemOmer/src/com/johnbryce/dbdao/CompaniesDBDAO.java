package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.db.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {
	
	private static Connection conn = null;

	
	private static final String ADD_QUERY ="";
	private static final String UPDATE_QUERY ="";
	private static final String DELETE_QUERY ="";
	private static final String GET_ALL_QUERY ="";
	private static final String GET_ONE_QUERY ="";
	private static final String IS_COMPANY_EXIST_QUERY ="";

	
	
	@Override
	public boolean isCompanyExist(String email, String password) {
		// TODO Auto-generated method stub
		return false;
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

			PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
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
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();
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

}
