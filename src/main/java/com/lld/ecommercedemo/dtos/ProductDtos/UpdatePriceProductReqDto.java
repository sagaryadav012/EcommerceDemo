package com.lld.ecommercedemo.dtos.ProductDtos;

import lombok.Data;

@Data
public class UpdatePriceProductReqDto {
    private long id;
    private double price;
}
