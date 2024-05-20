package com.lld.ecommercedemo.Controllers;

import com.lld.ecommercedemo.Exceptions.ProductNotFoundException;
import com.lld.ecommercedemo.Models.Product;
import com.lld.ecommercedemo.Services.ProductService;
import com.lld.ecommercedemo.Utils.AuthUtils;
import com.lld.ecommercedemo.dtos.ProductDtos.*;
import com.lld.ecommercedemo.dtos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private AuthUtils authUtils;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthUtils authUtils) {
        this.productService = productService;
        this.authUtils = authUtils;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id, @RequestHeader("Auth") String token){
        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

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
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ProductsResponseDto> getProducts(@RequestHeader("Auth") String token){

        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

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
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<CreateProductResponseDto> createProduct(@RequestBody CreateProductRequestDto requestDto, @RequestHeader("Auth") String token){
        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        try{
            Product product = productService.createProduct(requestDto.getTitle(), requestDto.getPrice(), requestDto.getDescription(),
                    requestDto.getImage(), requestDto.getCategoryName(), requestDto.getQuantity());
            responseDto.setProduct(product);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @PostMapping("/details")
    public List<Product> getProductsByIds(@RequestBody GetProductsByIdsReqDTO reqDTO){
        List<Long> productIds = reqDTO.getProductIds();
        return this.productService.getProductsByIds(productIds);
    }
    @PatchMapping("/updateImage")
    public ResponseEntity<ProductResponseDto> updateImage(@RequestBody UpdateImageProductReqDto reqDto, @RequestHeader("Auth") String token){
        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

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
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/updatePrice")
    public ResponseEntity<ProductResponseDto> updatePrice(@RequestBody UpdatePriceProductReqDto reqDto, @RequestHeader("Auth") String token){
        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

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
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @PatchMapping("/updateQuantity")
    public ResponseEntity<ProductResponseDto> updateQuantity(@RequestBody UpdateQuantityReqDto reqDto, @RequestHeader("Auth") String token){
        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        ProductResponseDto responseDto = new ProductResponseDto();
        try {
            Product product = productService.updateQuantity(reqDto.getId(), reqDto.getQuantity());
            responseDto.setProduct(product);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (ProductNotFoundException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable long id, @RequestHeader("Auth") String token){
        if(!authUtils.validate_token(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();
        try{
            productService.deleteProduct(id);
            productResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            productResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
}
