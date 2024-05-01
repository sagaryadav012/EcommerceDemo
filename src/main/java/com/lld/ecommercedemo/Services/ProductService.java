package com.lld.ecommercedemo.Services;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id) throws ProductNotFoundException;
    List<Product> getProducts();
    Product createProduct(String title, double price, String description, String image, String categoryName);
    Product updatePrice(long id, double updatedPrice) throws ProductNotFoundException;
    Product updateImage(long id, String updatedImage) throws ProductNotFoundException;
    void deleteProduct(long id) throws ProductNotFoundException;
}
