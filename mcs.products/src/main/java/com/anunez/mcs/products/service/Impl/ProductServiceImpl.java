package com.anunez.mcs.products.service.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.anunez.mcs.products.dto.ProductReq;
import com.anunez.mcs.products.model.Product;
import com.anunez.mcs.products.repository.ProductRepository;
import com.anunez.mcs.products.service.ProductService;
import com.anunez.mcs.products.utils.ProductMapperUtil;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductReq productReq) {
        return productRepository.save(ProductMapperUtil.mapToProduct(productReq));
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        LOG.info("Fetching product with ID: {}", id);
        return productRepository.findById(id)
                .or(() -> {
                    LOG.warn("Product with ID {} not found", id);
                    return Optional.empty();
                });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductReq productReq) {
        LOG.info("Updating product with ID: {}", id);
        return productRepository.findById(id)
                .map(product -> {
                    Product updatedProduct = ProductMapperUtil.mapToProduct(productReq);
                    updatedProduct.setId(product.getId());
                    return productRepository.save(updatedProduct);
                })
                .orElseGet(() -> {
                    LOG.warn("Product with ID {} not found", id);
                    return null;
                });
    }

    @Override
    public void deleteProduct(Long id) {
        LOG.info("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
    }
    
}
