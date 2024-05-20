package com.lld.ecommercedemo.dtos.ProductDtos;

import lombok.Data;

import java.util.List;
@Data
public class GetProductsByIdsReqDTO {
    private List<Long> productIds;
}
