package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Customer;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.db.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {
	private static Connection conn = null;

	
	private static final String ADD_QUERY ="";
	private static final String UPDATE_QUERY ="";
	private static final String DELETE_QUERY ="";
	private static final String GET_ALL_QUERY ="";
	private static final String GET_ONE_QUERY ="";
	private static final String IS_CUSTOMER_EXIST_QUERY ="";
	
	
	
	@Override
	public boolean isCustomerExist(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
			try {
				conn = ConnectionPool.getInstance().getConnection();

				PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
				statement.setInt(1, customer.getId());
				statement.setString(2, customer.getFirstName());
				statement.setString(3, customer.getLastName());
				statement.setString(3, customer.getEmail());
				statement.setString(4, customer.getPassword());			
				statement.executeUpdate();

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				ConnectionPool.getInstance().returnConnection(conn);
				conn = null;
			}
		}
	

	@Override
	public void upadateCustomer(Customer customer) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
			statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());			
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
			conn = null;
		}
	}
	

	@Override
	public void deleteCustomer(int customerId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
			statement.setInt(1, customerId);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;


	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(GET_ALL_QUERY);

			ResultSet resultSet = statement.executeQuery();
			
			
			while (resultSet.next()) {
				Customer customer = new Customer();
				
				customer.setId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(3));
				customer.setPassword(resultSet.getString(4));
				customers.add(customer);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return customers;
	}

	@Override
	public Customer getOneCustomer(int CustomerId) {
		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(GET_ONE_QUERY);
			statement.setInt(1, CustomerId);
			ResultSet resultSet = statement.executeQuery();

			
			while (resultSet.next()) {
				Customer customer = new Customer();
				
				customer.setId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(2));
				customer.setEmail(resultSet.getString(4));
				customer.setPassword(resultSet.getString(5));
				return customer;

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


