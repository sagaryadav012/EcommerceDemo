package com.lld.ecommercedemo.dtos.ProductDtos;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String categoryName;
}
