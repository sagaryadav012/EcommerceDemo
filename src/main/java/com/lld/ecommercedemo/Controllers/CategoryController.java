package com.lld.ecommercedemo.Controllers;

import com.lld.ecommercedemo.Exceptions.CategoryNotFoundException;
import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.Services.CategoryService;
import com.lld.ecommercedemo.Services.ProductService;
import com.lld.ecommercedemo.dtos.CategoryDtos.CategoriesResponseDto;
import com.lld.ecommercedemo.dtos.CategoryDtos.CategoryResponseDto;
import com.lld.ecommercedemo.dtos.CategoryDtos.CreateCategoryDto;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public Category createCategory(@RequestBody CreateCategoryDto dto){
        return categoryService.createCategory(dto.getName());
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable long id){
        CategoryResponseDto responseDto = new CategoryResponseDto();

        try {
            Category category = categoryService.getCategoryById(id);
            responseDto.setCategory(category);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (CategoryNotFoundException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
    @GetMapping
    public CategoriesResponseDto getAllCategories(){
        CategoriesResponseDto responseDto = new CategoriesResponseDto();
        try {
            List<Category> categories = categoryService.getAllCategories();
            responseDto.setCategory(categories);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
