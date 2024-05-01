package com.lld.ecommercedemo.Controllers;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.Services.ProductService;
import com.lld.ecommercedemo.dtos.ProductDtos.*;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable int id){
        ProductResponseDto responseDto = new ProductResponseDto();
        try{
            Product product = productService.getProductById(id);
            responseDto.setProduct(product);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @GetMapping("")
    public ProductsResponseDto getProducts(){
        ProductsResponseDto responseDto = new ProductsResponseDto();
        try{
            List<Product> products = productService.getProducts();
            responseDto.setProducts(products);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto requestDto){
        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        try{
            Product product = productService.createProduct(requestDto.getTitle(), requestDto.getPrice(), requestDto.getDescription(),
                    requestDto.getImage(), requestDto.getCategoryName());
            responseDto.setProduct(product);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
    @PatchMapping("/updateImage")
    public ProductResponseDto updateImage(@RequestBody UpdateImageProductReqDto reqDto){
        ProductResponseDto responseDto = new ProductResponseDto();
        try{
            Product product = productService.updateImage(reqDto.getId(), reqDto.getImage());
            responseDto.setProduct(product);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @PatchMapping("/updatePrice")
    public ProductResponseDto updatePrice(@RequestBody UpdatePriceProductReqDto reqDto){
        ProductResponseDto responseDto = new ProductResponseDto();
        try{
            Product product = productService.updatePrice(reqDto.getId(), reqDto.getPrice());
            responseDto.setProduct(product);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto deleteProduct(@PathVariable long id){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        try{
            productService.deleteProduct(id);
            productResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            productResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return productResponseDto;
    }
}
