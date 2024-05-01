package com.lld.ecommercedemo.Services;

import com.lld.ecommercedemo.Exceptions.CategoryNotFoundException;
import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);
    }
    public Category getCategoryById(long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Invalid Category ID"));
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
