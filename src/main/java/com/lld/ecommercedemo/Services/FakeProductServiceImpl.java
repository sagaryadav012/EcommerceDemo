package com.lld.ecommercedemo.Services;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Category;
import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.Utils.ProductUtils;
import com.lld.ecommercedemo.dtos.FakeProductServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeProductServiceImpl implements ProductService{

//    private RestTemplate restTemplate;
    private WebClient webClient;
    @Autowired
    public FakeProductServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
//        FakeProductServiceDto productServiceDto = this.restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceDto.class);
        FakeProductServiceDto productServiceDto = webClient.get()
                                                            .uri("https://fakestoreapi.com/products/" + id)
                                                            .retrieve()
                                                            .bodyToMono(FakeProductServiceDto.class).block();

        if(productServiceDto == null) throw new ProductNotFoundException("Product Id Is Invalid");
        return ProductUtils.convertDtoToProduct(productServiceDto);
    }

    @Override
    public List<Product> getProducts() {
        FakeProductServiceDto[] productServiceDtoS = webClient.get().uri("https://fakestoreapi.com/products").retrieve().bodyToMono(FakeProductServiceDto[].class).block();
        List<Product> products = new ArrayList<>();

        for (FakeProductServiceDto serviceDto : productServiceDtoS) {
            Product product = ProductUtils.convertDtoToProduct(serviceDto);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String categoryName, int quantity) {
        return null;
    }

    @Override
    public Product updatePrice(long id, double updatedPrice) {
        return null;
    }

    @Override
    public Product updateImage(long id, String updatedImage) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {
    }

    @Override
    public Product updateQuantity(long id, int quantity) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getProductsByIds(List<Long> productIds) {
        return null;
    }
}
