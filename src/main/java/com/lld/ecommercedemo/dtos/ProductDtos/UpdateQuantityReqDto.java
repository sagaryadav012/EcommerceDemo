package com.lld.ecommercedemo.dtos.ProductDtos;

import lombok.Data;

@Data
public class UpdateQuantityReqDto {
    private long id;
    private int quantity;
}
