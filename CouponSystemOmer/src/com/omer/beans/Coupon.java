package com.omer.beans;

import java.util.Date;
import com.omer.beans.Company;

public class Coupon {

	private int id;
	private int CompanyId;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Coupon() {
		// TODO Auto-generated constructor stub
	}

	public Coupon(int id, int companyId, Category cateory, String title, String description, Date startDate,
			Date endDate) {
		super();
		this.id = id;
		CompanyId = companyId;
		this.category = cateory;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Coupon(int id, int companyId, Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) {
		super();
		this.id = id;
		CompanyId = companyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCateory(Category cateory) {
		this.category = cateory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", CompanyId=" + CompanyId + ", cateory=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
