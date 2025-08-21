package com.anunez.mcs.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anunez.mcs.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
    
}
