package com.lld.ecommercedemo.Services;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    CategoryService categoryService;

    @Autowired
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Invalid product id"));
        return product;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String categoryName, int quantity) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        Category category = categoryService.createCategory(categoryName);
        product.setCategory(category);
        product.setAvailableQuantity(quantity);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByIds(List<Long> productIds) {
        return this.productRepository.findAllById(productIds);
    }

    @Override
    public Product updatePrice(long id, double updatedPrice) throws ProductNotFoundException {
        Product product = getProductById(id);
        product.setPrice(updatedPrice);
        return productRepository.save(product);
    }

    @Override
    public Product updateImage(long id, String updatedImage) throws ProductNotFoundException {
        Product product = getProductById(id);
        product.setImage(updatedImage);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Product product = getProductById(id);
        productRepository.deleteById(product.getId());
    }

    @Override
    public Product updateQuantity(long id, int quantity) throws ProductNotFoundException {
        Product product = getProductById(id);
        product.setAvailableQuantity(quantity);
        return productRepository.save(product);
    }
}
