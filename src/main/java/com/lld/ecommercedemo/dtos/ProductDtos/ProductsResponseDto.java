package com.lld.ecommercedemo.dtos.ProductDtos;

import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import lombok.Data;

import java.util.List;

@Data
public class ProductsResponseDto {
    private List<Product> products;
    private String message;
    private ResponseStatus responseStatus;
}
