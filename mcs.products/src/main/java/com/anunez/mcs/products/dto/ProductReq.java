package com.anunez.mcs.products.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductReq(String code, String sku, String name, String description, Double price) {}
