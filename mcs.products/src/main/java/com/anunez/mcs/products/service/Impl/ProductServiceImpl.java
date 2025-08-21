package com.anunez.mcs.products.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anunez.mcs.products.dto.ProductReq;
import com.anunez.mcs.products.dto.ProductRes;
import com.anunez.mcs.products.model.Product;
import com.anunez.mcs.products.repository.ProductRepository;
import com.anunez.mcs.products.service.ProductService;
import com.anunez.mcs.products.utils.ProductMapperUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductRes createProduct(ProductReq productReq) {
        log.info("Creating new product with code: {}", productReq.code());
        Product saveProduct = productRepository.save(ProductMapperUtil.mapToProduct(productReq));
        return ProductMapperUtil.mapToProductRes(saveProduct);
    }

    @Override
    public Optional<ProductRes> getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);
        return productRepository.findById(id)
                .map(ProductMapperUtil::mapToProductRes)
                .or(() -> {
                    log.warn("Product with ID {} not found", id);
                    return Optional.empty();
                });
    }

    @Override
    public List<ProductRes> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapperUtil::mapToProductRes)
                .toList();
    }

    @Override
    public ProductRes updateProduct(Long id, ProductReq productReq) {
        log.info("Updating product with ID: {}", id);

        Product productToUpdate = productRepository.findById(id)
                .map(existingProduct -> {
                    Product updatedProduct = ProductMapperUtil.mapToProduct(productReq);
                    updatedProduct.setId(existingProduct.getId());
                    return productRepository.save(updatedProduct);
                })
                .orElseGet(() -> {
                    log.warn("Product with ID {} not found", id);
                    throw new RuntimeException("Product not found");
                });

        return ProductMapperUtil.mapToProductRes(productToUpdate);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
    }
    
}
