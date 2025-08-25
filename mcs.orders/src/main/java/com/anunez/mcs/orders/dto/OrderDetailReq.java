package com.anunez.mcs.orders.dto;

public record OrderDetailReq(Long id, Long productId, String productName, Integer quantity, Double price, String sku) {
}
