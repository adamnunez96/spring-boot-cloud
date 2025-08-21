package com.anunez.mcs.products.service;

import java.util.List;
import java.util.Optional;

import com.anunez.mcs.products.dto.ProductReq;
import com.anunez.mcs.products.model.Product;

public interface ProductService {
    Product createProduct(ProductReq productReq);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductReq productReq);
    void deleteProduct(Long id);
}
