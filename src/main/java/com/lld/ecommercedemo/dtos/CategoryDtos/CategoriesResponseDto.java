package com.lld.ecommercedemo.dtos.CategoryDtos;

import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import lombok.Data;

import java.util.List;

@Data
public class CategoriesResponseDto {
    private List<Category> category;
    private String message;
    private ResponseStatus responseStatus;
}
