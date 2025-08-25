package com.anunez.mcs.orders.dto;

import java.util.List;

public record OrderRes(Long id, Long customerId, String status, String orderNumber, List<OrderDetailRes> orderDetails) {
}
