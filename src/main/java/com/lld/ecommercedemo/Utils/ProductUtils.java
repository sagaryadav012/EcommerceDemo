package com.lld.ecommercedemo.Utils;

import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.dtos.FakeProductServiceDto;

public class ProductUtils {
    public static Product convertDtoToProduct(FakeProductServiceDto productServiceDto){
        Product product = new Product();
        product.setId(productServiceDto.getId());
        product.setTitle(productServiceDto.getTitle());
        product.setPrice(productServiceDto.getPrice());
        product.setImage(productServiceDto.getImage());
        product.setDescription(productServiceDto.getDescription());
        Category category = new Category();
        category.setName(productServiceDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
