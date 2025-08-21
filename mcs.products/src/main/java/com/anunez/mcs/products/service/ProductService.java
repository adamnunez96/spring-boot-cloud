package com.anunez.mcs.products.service;

import java.util.List;
import java.util.Optional;

import com.anunez.mcs.products.dto.ProductReq;
import com.anunez.mcs.products.dto.ProductRes;

public interface ProductService {
    ProductRes createProduct(ProductReq productReq);
    Optional<ProductRes> getProductById(Long id);
    List<ProductRes> getAllProducts();
    ProductRes updateProduct(Long id, ProductReq productReq);
    void deleteProduct(Long id);
}
