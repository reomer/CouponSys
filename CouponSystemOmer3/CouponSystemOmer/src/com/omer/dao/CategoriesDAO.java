package com.omer.dao;

import com.omer.beans.Category;

//couponsproject.categories
public interface CategoriesDAO {

	int getCategoryID(Category category);

	Category getCategoryName(int ID);
}

