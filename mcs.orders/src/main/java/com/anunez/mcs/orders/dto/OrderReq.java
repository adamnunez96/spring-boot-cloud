package com.anunez.mcs.orders.dto;

import java.util.List;

public record OrderReq(Long customerId, String status, List<OrderDetailReq> orderDetails) {
}
