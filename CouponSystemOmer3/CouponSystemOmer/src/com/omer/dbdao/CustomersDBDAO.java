package com.omer.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.omer.beans.Company;
import com.omer.beans.Customer;
import com.omer.dao.CustomersDAO;
import com.omer.db.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {
	private static Connection conn = null;

	
	private static final String ADD_QUERY ="INSERT INTO `coupon_sys`.`CUSTOMERS` (`first_name`, `last_name`, `email`,`password`) VALUES (?,?,?,?);\n" + 
			"";
	private static final String UPDATE_QUERY ="UPDATE `coupon_sys`.`CUSTOMERS` SET `first_name`=?, `last_name`=?, `email`=?, `password`=? WHERE `id`=?;\n" + 
			"";
	private static final String DELETE_QUERY ="DELETE FROM `coupon_sys`.`CUSTOMERS` WHERE `id`=?;\n" + 
			"";
	private static final String GET_ALL_QUERY ="SELECT*FROM coupon_sys.CUSTOMERS;";
	private static final String GET_CUSTOMER_ID_BY_EMAIL_PASSWORD = "SELECT ID FROM coupon_sys.CUSTOMERS where email= ? AND password= ?";

	private static final String GET_ONE_QUERY ="SELECT*FROM coupon_sys.CUSTOMERS WHERE id=?";
	private static final String IS_CUSTOMER_EXIST_QUERY ="SELECT*FROM coupon_sys.CUSTOMERS WHERE id=?";
	
	
	
	@Override
	public boolean isCustomerExist(String email, String password) {
		boolean isExist = false;

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(IS_CUSTOMER_EXIST_QUERY);
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();
					
			while (resultSet.next()) {
				
				 isExist=true; 

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;
		return isExist;
	}

	
	
	
	
	@Override
	public void addCustomer(Customer customer) {
			try {
				conn = ConnectionPool.getInstance().getConnection();

				PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
				statement.setInt(1, customer.getId());
				statement.setString(2, customer.getFirstName());
				statement.setString(3, customer.getLastName());
				statement.setString(4, customer.getEmail());
				statement.setString(5, customer.getPassword());			
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
	
	public int getCustomerID(String email, String password) {
		Connection conn = null;
		int id = 0;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(GET_CUSTOMER_ID_BY_EMAIL_PASSWORD);
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

	


