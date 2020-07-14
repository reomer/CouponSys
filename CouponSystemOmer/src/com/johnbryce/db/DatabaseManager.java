package com.johnbryce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

	private static Connection conn = null;
	private static final String user = "root";
	private static final String pass = "1234";
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
	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `coupon_sys`.`CATEGORIES` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `coupon_sys`.`CUSTOMER_VS_CUOPONS` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n" + "  INDEX `cuopon_id_idx` (`coupon_id` ASC),\r\n"
			+ "  CONSTRAINT `customer_id`\r\n" + "    FOREIGN KEY (`customer_id`)\r\n"
			+ "    REFERENCES `coupon_sys`.`CUSTOMERS` (`id`)\r\n" + "    ON DELETE NO ACTION\r\n"
			+ "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `cuopon_id`\r\n" + "    FOREIGN KEY (`coupon_id`)\r\n"
			+ "    REFERENCES `coupon_sys`.`COUPONS` (`id`)\r\n" + "    ON DELETE NO ACTION\r\n"
			+ "    ON UPDATE NO ACTION);";

	private static final String DROP_TABLE_COMPANIES = "DROP TABLE 'coupon_sys'.'companies';";
	private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE 'coupon_sys'.'customers';";
	private static final String DROP_TABLE_CATEGORIES = "DROP TABLE 'coupon_sys'.'categories';";
	private static final String DROP_TABLE_COUPONS = "DROP TABLE 'coupon_sys'.'coupons';";
	private static final String DROP_TABLE_CUSTOMERS_VS_COUPONS = "DROP TABLE 'coupon_sys'.'customers_vs_coupons';";

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

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}

	public static String getUrl() {
		return url;
	}

	public static void dropAndCreateDatabase() {
		runSqlQuery(DROP_TABLE_COMPANIES);
		runSqlQuery(DROP_TABLE_CUSTOMERS);
		runSqlQuery(DROP_TABLE_CATEGORIES);
		runSqlQuery(DROP_TABLE_COUPONS);
		runSqlQuery(DROP_TABLE_CUSTOMERS_VS_COUPONS);

		runSqlQuery(CREATE_TABLE_COMPANIES);
		runSqlQuery(CREATE_TABLE_CUSTOMERS);
		runSqlQuery(CREATE_TABLE_CATEGORIES);
		runSqlQuery(CREATE_TABLE_COUPONS);
		runSqlQuery(CREATE_TABLE_CUSTOMERS_VS_COUPONS);

	}

}
