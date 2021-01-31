package com.omer.dao;

import com.omer.beans.Category;

public interface CategoriesDAO {

	public int getCategoryID(Category category);

	public Category getCategoryName(int ID);
	
	public void addCategoryTest();

}

