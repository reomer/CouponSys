package com.omer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

	private static Connection conn = null;
	private static final String user = "root";
	private static final String pass = "Innobi2137";
	public static final String url = "jdbc:mysql://localhost:3306/coupon_sys?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";

	private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `coupon_sys`.`COMPANIES` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `coupon_sys`.`CUSTOMERS` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `password` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `coupon_sys`.`COUPONS` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` INT NOT NULL,\r\n"
			+ "  `category_id` INT NOT NULL,\r\n" + "  `title` VARCHAR(45) NOT NULL,\r\n"
			+ "  `description` VARCHAR(45) NOT NULL,\r\n" + "\n" + "  `start_date` DATE NOT NULL,\r\n"
			+ "  `end_date` DATE NOT NULL,\r\n" + "  `amount` INT NOT NULL,\r\n" + "  `price` DOUBLE NOT NULL,\r\n"
			+ "  `image` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "  INDEX `company_id_idx` (`company_id` ASC),\r\n" + "  INDEX `category_id_idx` (`category_id` ASC),\r\n"
			+ "  CONSTRAINT `company_id`\r\n" + "    FOREIGN KEY (`company_id`)\r\n"
			+ "    REFERENCES `coupon_sys`.`COMPANIES` (`id`)\r\n" + "    ON DELETE NO ACTION\r\n"
			+ "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `category_id`\r\n" + "    FOREIGN KEY (`category_id`)\r\n"
			+ "    REFERENCES `coupon_sys`.`CATEGORIES` (`id`)\r\n" + "    ON DELETE NO ACTION\r\n"
			+ "    ON UPDATE NO ACTION);";
	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `coupon_sys`.`CATEGORIES`"
			+ "(`ID` INT NOT NULL AUTO_INCREMENT, `NAME` VARCHAR(45) NULL," + " PRIMARY KEY (`ID`));";
	private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `coupon_sys`.`CUSTOMER_VS_COUPONS` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n" + "  INDEX `cuopon_id_idx` (`coupon_id` ASC),\r\n"
			+ "  CONSTRAINT `customer_id`\r\n" + "    FOREIGN KEY (`customer_id`)\r\n"
			+ "    REFERENCES `coupon_sys`.`CUSTOMERS` (`id`)\r\n" + "    ON DELETE NO ACTION\r\n"
			+ "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `cuopon_id`\r\n" + "    FOREIGN KEY (`coupon_id`)\r\n"
			+ "    REFERENCES `coupon_sys`.`COUPONS` (`id`)\r\n" + "    ON DELETE NO ACTION\r\n"
			+ "    ON UPDATE NO ACTION);";

	private static final String DROP_TABLE_COMPANIES = "DROP TABLE `coupon_sys`.`COMPANIES`;";
	private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE `coupon_sys`.`CUSTOMERS`;";
	private static final String DROP_TABLE_CATEGORIES = "DROP TABLE `coupon_sys`.`CATEGORIES`;";
	private static final String DROP_TABLE_COUPONS = "DROP TABLE `coupon_sys`.`COUPONS`;";
	private static final String DROP_TABLE_CUSTOMERS_VS_COUPONS = "DROP TABLE  `coupon_sys`.`CUSTOMER_VS_COUPONS`;";

	public static void runSqlQuery(String sql) {

		try {
			conn = ConnectionPool.getInstance().getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
		conn = null;

	}

	public static void createTableCategories(String sql1) throws SQLException {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statment = conn.prepareStatement(sql1);
			statment.executeUpdate();
			String sql = null;

			sql = "INSERT INTO `coupon_sys`.`CATEGORIES` (`NAME`) VALUES ('FOOD');";
			statment = conn.prepareStatement(sql);
			statment.executeUpdate();
			sql = "INSERT INTO `coupon_sys`.`CATEGORIES` (`NAME`) VALUES ('ELECTRICITY');";
			statment = conn.prepareStatement(sql);
			statment.executeUpdate();
			sql = "INSERT INTO `coupon_sys`.`CATEGORIES` (`NAME`) VALUES ('SPORT');";
			statment = conn.prepareStatement(sql);
			statment.executeUpdate();
			sql = "INSERT INTO `coupon_sys`.`CATEGORIES` (`NAME`) VALUES ('VACATION');";
			statment = conn.prepareStatement(sql);
			statment.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
			conn = null;
		}

	}

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}

	public static String getUrl() {
		return url;
	}

	public static void dropDatabase() {
		runSqlQuery(DROP_TABLE_CUSTOMERS_VS_COUPONS);
		runSqlQuery(DROP_TABLE_CUSTOMERS);
		runSqlQuery(DROP_TABLE_COUPONS);
		runSqlQuery(DROP_TABLE_COMPANIES);
		runSqlQuery(DROP_TABLE_CATEGORIES);
	}

	public static void createDatabase() throws SQLException {
		runSqlQuery(CREATE_TABLE_COMPANIES);
		runSqlQuery(CREATE_TABLE_CUSTOMERS);
		createTableCategories(CREATE_TABLE_CATEGORIES);
		runSqlQuery(CREATE_TABLE_COUPONS);
		runSqlQuery(CREATE_TABLE_CUSTOMERS_VS_COUPONS);

	}

}
