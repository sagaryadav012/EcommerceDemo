package com.lld.ecommercedemo.Controllers;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        try {
            return this.productService.getProductById(id);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
}
