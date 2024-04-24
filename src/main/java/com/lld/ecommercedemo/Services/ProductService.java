package com.lld.ecommercedemo.Services;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(int id) throws ProductNotFoundException;
    List<Product> getProducts();
}
