package com.anunez.mcs.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anunez.mcs.products.dto.ProductReq;
import com.anunez.mcs.products.dto.ProductRes;
import com.anunez.mcs.products.service.ProductService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/${version}/products")
public class ProductController {

    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductRes createProduct(@RequestBody @Valid ProductReq productReq) {
        return productService.createProduct(productReq);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<ProductRes> getProductById(@PathVariable(required = true) Long id) {
        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductRes> getAllProducts() {
        return productService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ProductRes updateProduct(@PathVariable(required = true) Long id, @RequestBody ProductReq productReq) {
        return productService.updateProduct(id, productReq);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
