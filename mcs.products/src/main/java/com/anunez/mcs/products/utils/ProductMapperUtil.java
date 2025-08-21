package com.anunez.mcs.products.utils;

import com.anunez.mcs.products.dto.ProductReq;
import com.anunez.mcs.products.model.Product;

public class ProductMapperUtil {

    private ProductMapperUtil() {}
    
    public static Product mapToProduct(ProductReq productReq) {
        return Product.builder()
                    .code(productReq.code())
                    .sku(productReq.sku())
                    .name(productReq.name())
                    .description(productReq.description())
                    .price(productReq.price())
                .build();
    }

}
