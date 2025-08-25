package com.anunez.mcs.orders.utils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.anunez.mcs.orders.dto.OrderDetailReq;
import com.anunez.mcs.orders.dto.OrderDetailRes;
import com.anunez.mcs.orders.dto.OrderReq;
import com.anunez.mcs.orders.dto.OrderRes;
import com.anunez.mcs.orders.model.Order;
import com.anunez.mcs.orders.model.OrderDetail;

public class OrderMapper {

    private OrderMapper() {}

    public static Order toEntity(OrderReq orderReq) {
        List<OrderDetail> orderDetails = orderReq.orderDetails().stream()
                .map(OrderMapper::toEntity)
                .collect(Collectors.toList());
        
        return Order.builder()
                .customerId(orderReq.customerId())
                .status(orderReq.status())
                .orderNumber(UUID.randomUUID().toString())
                .orderDetails(orderDetails)
                .build();
    }

    public static OrderDetail toEntity(OrderDetailReq orderDetailReq) {
        return OrderDetail.builder()
                .id(orderDetailReq.id())
                .productId(orderDetailReq.productId())
                .productName(orderDetailReq.productName())
                .quantity(orderDetailReq.quantity())
                .price(orderDetailReq.price())
                .sku(orderDetailReq.sku())
                .build();
    }

    public static OrderRes toDto(Order order) {
        List<OrderDetailRes> orderDetails = order.getOrderDetails().stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
        
        return new OrderRes(
                order.getId(),
                order.getCustomerId(),
                order.getStatus(),
                order.getOrderNumber(),
                orderDetails
        );
    }

    public static OrderDetailRes toDto(OrderDetail orderDetail) {
        return new OrderDetailRes(
                orderDetail.getId(),
                orderDetail.getProductId(),
                orderDetail.getProductName(),
                orderDetail.getQuantity(),
                orderDetail.getPrice(),
                orderDetail.getSku()
        );
    }

}
