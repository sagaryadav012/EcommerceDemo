package com.lld.ecommercedemo.dtos.CategoryDtos;

import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Category category;
    private String message;
    private ResponseStatus responseStatus;
}
