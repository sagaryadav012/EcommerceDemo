package com.lld.ecommercedemo.Services;

import com.lld.ecommercedemo.Exceptions.CategoryNotFoundException;
import com.lld.ecommercedemo.Models.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String name);
    Category getCategoryById(long id) throws CategoryNotFoundException;
    List<Category> getAllCategories();
}
