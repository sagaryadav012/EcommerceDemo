package com.lld.ecommercedemo.dtos.ProductDtos;

import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Product product;
    private String message;
    private ResponseStatus responseStatus;
}
